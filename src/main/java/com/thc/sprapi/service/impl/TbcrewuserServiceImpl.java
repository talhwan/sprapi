package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbcrewuser;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewuserDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbcrewuserMapper;
import com.thc.sprapi.repository.TbcrewuserRepository;
import com.thc.sprapi.service.TbcrewuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbcrewuserServiceImpl implements TbcrewuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcrewuserRepository tbcrewuserRepository;
    private final TbcrewuserMapper tbcrewuserMapper;
    public TbcrewuserServiceImpl(
            TbcrewuserRepository tbcrewuserRepository
            ,TbcrewuserMapper tbcrewuserMapper
    ) {
        this.tbcrewuserRepository = tbcrewuserRepository;
        this.tbcrewuserMapper = tbcrewuserMapper;
    }

    public TbcrewuserDto.TbcrewuserAfterCreateDto join(TbcrewuserDto.TbcrewuserCreateDto params){
        Tbcrewuser tbcrewuser = tbcrewuserRepository.findByTbcrewIdAndTbuserId(params.getTbcrewId(), params.getTbuserId());
        if(tbcrewuser == null){
            tbcrewuser = tbcrewuserRepository.save(params.toEntity());
        }
        return tbcrewuser.toAfterCreateDto();
    }

    /**/
    public TbcrewuserDto.TbcrewuserAfterCreateDto create(TbcrewuserDto.TbcrewuserCreateDto params){
        return tbcrewuserRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbcrewuserDto.TbcrewuserAfterUpdateDto update(TbcrewuserDto.TbcrewuserUpdateDto params){
        Tbcrewuser tbcrewuser = tbcrewuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbcrewuser.setDeleted(params.getDeleted());
        }
        if(params.getProcess() != null){
            tbcrewuser.setProcess(params.getProcess());
        }
        if(params.getCate() != null){
            tbcrewuser.setCate(params.getCate());
        }
        if(params.getContent() != null){
            tbcrewuser.setContent(params.getContent());
        }
        tbcrewuserRepository.save(tbcrewuser);
        return tbcrewuser.toAfterUpdateDto();
    }
    public TbcrewuserDto.TbcrewuserAfterUpdateDto delete(TbcrewuserDto.TbcrewuserUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbcrewuser tbcrewuser = tbcrewuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbcrewuserRepository.delete(tbcrewuser);
        return tbcrewuser.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbcrewuserDto.TbcrewuserUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbcrewuserDto.TbcrewuserSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbcrewuserDto.TbcrewuserSelectDto get(String id){
        return tbcrewuserMapper.detail(id);
    }
    public List<TbcrewuserDto.TbcrewuserSelectDto> list(TbcrewuserDto.TbcrewuserListDto params){
        return addListDetails(tbcrewuserMapper.list(params));
    }
    public List<TbcrewuserDto.TbcrewuserSelectDto> moreList(TbcrewuserDto.TbcrewuserMoreListDto params){
        params.afterBuild();
        return addListDetails(tbcrewuserMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbcrewuserDto.TbcrewuserSelectDto> pagedList(TbcrewuserDto.TbcrewuserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbcrewuserMapper.pagedListCount(params)), addListDetails(tbcrewuserMapper.pagedList(params)));
    }

    public List<TbcrewuserDto.TbcrewuserSelectDto> addListDetails(List<TbcrewuserDto.TbcrewuserSelectDto> a_list){
        List<TbcrewuserDto.TbcrewuserSelectDto> result_list = new ArrayList<>();
        for(TbcrewuserDto.TbcrewuserSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
