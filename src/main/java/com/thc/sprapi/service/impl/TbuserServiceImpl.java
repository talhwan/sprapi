package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.RoleType;
import com.thc.sprapi.domain.Tbuser;
import com.thc.sprapi.domain.TbuserRoleType;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.exception.AlreadyExistDataException;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbuserMapper;
import com.thc.sprapi.repository.RoleTypeRepository;
import com.thc.sprapi.repository.TbuserRepository;
import com.thc.sprapi.repository.TbuserRoleTypeRepository;
import com.thc.sprapi.security.ExternalProperties;
import com.thc.sprapi.security.JwtTokenDto;
import com.thc.sprapi.service.AuthService;
import com.thc.sprapi.service.TbuserService;
import com.thc.sprapi.util.OknameCert;
import com.thc.sprapi.util.SnsLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbuserRepository tbuserRepository;
    private final RoleTypeRepository roleTypeRepository;
    private final TbuserRoleTypeRepository tbuserRoleTypeRepository;
    private final TbuserMapper tbuserMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;
    private final ExternalProperties externalProperties;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            , RoleTypeRepository roleTypeRepository
            , TbuserRoleTypeRepository tbuserRoleTypeRepository
            , TbuserMapper tbuserMapper
            , BCryptPasswordEncoder bCryptPasswordEncoder
            , AuthService authService
            , ExternalProperties externalProperties
    ) {
        this.tbuserRepository = tbuserRepository;
        this.roleTypeRepository = roleTypeRepository;
        this.tbuserRoleTypeRepository = tbuserRoleTypeRepository;
        this.tbuserMapper = tbuserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authService = authService;
        this.externalProperties = externalProperties;
    }

    public TbuserDto.TbuserOkcertTokenDto okcert(String rootPath) throws Exception {
        //OknameCert oc = new OknameCert(request, "와이엘컴퍼니", NaverWorksMailUtil.host_name, NaverWorksMailUtil.host_name+"/app/tbuser/insert_result");
        OknameCert oc;
        TbuserDto.TbuserOkcertTokenDto result = null;
        //String baseUrl = "https://home.muincaps.com";
        String baseUrl = "http://localhost:8080";

        oc = new OknameCert(rootPath, "와이엘컴퍼니",
                baseUrl,
                baseUrl
                        //+ "/tbuser/okcert/result"
                        +"/app/tbuser/insert_result"
        );
        result = oc.getToken();

        /*
        try{
            oc = new OknameCert(rootPath, "와이엘컴퍼니",
                    baseUrl,
                    baseUrl
                            //+ "/tbuser/okcert/result"
                            +"/app/tbuser/insert_result"
            );
            try{
                result = oc.getToken();
                logger.info("result : " + result);
            } catch (Exception e){
                logger.info("error2");
            }
        } catch (Exception e){
            logger.info("error1");
        }
         */

        return result;
    }
    public JwtTokenDto naver(String token){
        return sns(SnsLogin.naver(token));
    }
    public JwtTokenDto sns(TbuserDto.TbuserCreateDto params){
        Tbuser tbuser = tbuserRepository.findByUsername(params.getUsername());
        if(tbuser == null){
            String nick = UUID.randomUUID().toString().replace("-", "").substring(0,12);
            params.setNick(nick);
            create(params);
            tbuser = tbuserRepository.findByUsername(params.getUsername());
        }
        String refreshToken = authService.createRefreshToken(tbuser.getId());
        JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);
        jwtTokenDto.setRefreshToken(externalProperties.getTokenPrefix() + jwtTokenDto.getRefreshToken());
        jwtTokenDto.setAccessToken(externalProperties.getTokenPrefix() + jwtTokenDto.getAccessToken());

        return jwtTokenDto;
    }
    public TbuserDto.TbuserAfterCreateDto signup(TbuserDto.TbuserCreateDto params){
        //사용자는 중복체크가 필요합니다!!
        Tbuser tbuser = tbuserRepository.findByUsername(params.getUsername());
        if(tbuser == null){
            //닉네임은 그냥 자동 생성..
            String nick = UUID.randomUUID().toString().replace("-", "").substring(0,12);
            params.setNick(nick);
            params.setSfrom("DIRECT");
            params.setProcess("0");
        } else {
            throw new AlreadyExistDataException("username already exist!");
        }
        return create(params);
    }
    public TbuserDto.TbuserAfterUpdateDto logout(TbuserDto.TbuserUpdateDto params){
        Tbuser tbuser = tbuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        authService.revokeRefreshToken(params.getId());
        return tbuser.toAfterUpdateDto();
    }

    /**/

    public TbuserDto.TbuserAfterCreateDto create(TbuserDto.TbuserCreateDto params){
        //비번 암호화를 위한 코드
        String rawPassword = params.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        params.setPassword(encPassword);

        Tbuser tbuser = tbuserRepository.save(params.toEntity());

        //TO-DO
        //권한은 그냥 ROLE_USER 로 강제 저장함!(임시코드)
        RoleType roleType = roleTypeRepository.findByTypeName("ROLE_USER");
        TbuserRoleType tbuserRoleType = TbuserRoleType.of(tbuser, roleType);
        tbuserRoleTypeRepository.save(tbuserRoleType);
        //

        return tbuser.toAfterCreateDto();
    }
    public TbuserDto.TbuserAfterUpdateDto update(TbuserDto.TbuserUpdateDto params){
        Tbuser tbuser = tbuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        logger.info("!!!!!!!!!!!!!!!!" + params.getContent());
        if(params.getDeleted() != null){
            tbuser.setDeleted(params.getDeleted());
        }
        if(params.getPassword() != null){
            tbuser.setPassword(params.getPassword());
        }
        if(params.getNick() != null){
            Tbuser tbuserTemp = tbuserRepository.findByNick(params.getNick());
            if(tbuserTemp != null){
                throw new AlreadyExistDataException("Unique Key Error");
            }
            tbuser.setNick(params.getNick());
        }
        if(params.getSfrom() != null){
            tbuser.setSfrom(params.getSfrom());
        }
        if(params.getProcess() != null){
            tbuser.setProcess(params.getProcess());
        }
        if(params.getName() != null){
            tbuser.setName(params.getName());
        }
        if(params.getPhone() != null){
            tbuser.setPhone(params.getPhone());
        }
        if(params.getMpic() != null){
            tbuser.setMpic(params.getMpic());
        }
        if(params.getContent() != null){
            tbuser.setContent(params.getContent());
        }
        tbuserRepository.save(tbuser);
        return tbuser.toAfterUpdateDto();
    }

    public TbuserDto.TbuserAfterUpdateDto delete(TbuserDto.TbuserUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbuserDto.TbuserUpdateDto.builder().id(each).build());
        }
        return params;
    }
    public TbuserDto.TbuserSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbuserDto.TbuserSelectDto get(String id){
        return tbuserMapper.detail(id);
    }
    public List<TbuserDto.TbuserSelectDto> list(TbuserDto.TbuserListDto params){
        return addListDetails(tbuserMapper.list(params));
    }
    public List<TbuserDto.TbuserSelectDto> moreList(TbuserDto.TbuserMoreListDto params){
        params.afterBuild();
        return addListDetails(tbuserMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbuserDto.TbuserSelectDto> pagedList(TbuserDto.TbuserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbuserMapper.pagedListCount(params)), addListDetails(tbuserMapper.pagedList(params)));
    }

    public List<TbuserDto.TbuserSelectDto> addListDetails(List<TbuserDto.TbuserSelectDto> a_list){
        List<TbuserDto.TbuserSelectDto> result_list = new ArrayList<>();
        for(TbuserDto.TbuserSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
