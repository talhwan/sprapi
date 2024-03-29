package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbpost;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoAccessGrantException;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbpostMapper;
import com.thc.sprapi.repository.TbpostRepository;
import com.thc.sprapi.service.TbpostService;
import com.thc.sprapi.service.TbpostfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    private final TbpostfileService tbpostfileService;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            ,TbpostMapper tbpostMapper
            ,TbpostfileService tbpostfileService
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
        this.tbpostfileService = tbpostfileService;
    }

    public TbpostDto.TbpostAfterCreateDto create(TbpostDto.TbpostCreateDto params){
        //혹시 tbuserId가 비어있으면 현재 접속한 사람의 정보 집어넣기
        if(params.getTbuserId() == null){
            params.setTbuserId(params.getNowTbuserId());
        }
        TbpostDto.TbpostAfterCreateDto result = tbpostRepository.save(params.toEntity()).toAfterCreateDto();
        String[] pics = params.getPics();
        String[] types = params.getTypes();
        for(int i=0;i<pics.length;i++){
            tbpostfileService.create(new TbpostfileDto.TbpostfileCreateDto(result.getId(), types[i], pics[i]));
        }
        return result;
    }
    public TbpostDto.TbpostAfterUpdateDto update(TbpostDto.TbpostUpdateDto params){
        Tbpost tbpost = tbpostRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(!params.isNowGrant() && !tbpost.getTbuserId().equals(params.getNowTbuserId())){
            //관리자도 아니고, 본인 것도 아닌 경우 권한 없음
            throw new NoAccessGrantException("");
        }

        if(params.getTitle() != null){
            tbpost.setTitle(params.getTitle());
        }
        if(params.getContent() != null){
            tbpost.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbpost.setDeleted(params.getDeleted());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toAfterUpdateDto();
    }
    public TbpostDto.TbpostAfterUpdateDto delete(TbpostDto.TbpostUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            /*
            TbpostDto.TbpostUpdateDto aa = new TbpostDto.TbpostUpdateDto();
            aa.setId(each);
            delete(aa);
             */
            delete(TbpostDto.TbpostUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbpostDto.TbpostSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbpostDto.TbpostSelectDto get(String id){
        TbpostDto.TbpostSelectDto result = tbpostMapper.detail(id);
        result.setPics(tbpostfileService.list(new TbpostfileDto.TbpostfileListDto("N", id,"image",null)));
        result.setFiles(tbpostfileService.list(new TbpostfileDto.TbpostfileListDto("N", id,"file",null)));
        return result;
    }
    public List<TbpostDto.TbpostSelectDto> list(TbpostDto.TbpostListDto params){
        return addListDetails(tbpostMapper.list(params));
    }
    public List<TbpostDto.TbpostSelectDto> moreList(TbpostDto.TbpostMoreListDto params){
        params.afterBuild();
        return addListDetails(tbpostMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbpostDto.TbpostSelectDto> pagedList(TbpostDto.TbpostPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbpostMapper.pagedListCount(params)), addListDetails(tbpostMapper.pagedList(params)));
    }

    public List<TbpostDto.TbpostSelectDto> addListDetails(List<TbpostDto.TbpostSelectDto> a_list){
        List<TbpostDto.TbpostSelectDto> result_list = new ArrayList<>();
        for(TbpostDto.TbpostSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
