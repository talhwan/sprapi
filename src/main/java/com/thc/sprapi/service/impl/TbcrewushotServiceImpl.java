package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbcrewushot;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbcrewMapper;
import com.thc.sprapi.mapper.TbcrewushotMapper;
import com.thc.sprapi.repository.TbcrewushotRepository;
import com.thc.sprapi.service.TbcrewuserService;
import com.thc.sprapi.service.TbcrewushotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbcrewushotServiceImpl implements TbcrewushotService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcrewushotRepository tbcrewushotRepository;
    private final TbcrewushotMapper tbcrewushotMapper;
    private final TbcrewMapper tbcrewMapper;
    private final TbcrewuserService tbcrewuserService;
    public TbcrewushotServiceImpl(
            TbcrewushotRepository tbcrewushotRepository
            , TbcrewushotMapper tbcrewushotMapper
            , TbcrewMapper tbcrewMapper
            , TbcrewuserService tbcrewuserService
    ) {
        this.tbcrewushotRepository = tbcrewushotRepository;
        this.tbcrewushotMapper = tbcrewushotMapper;
        this.tbcrewMapper = tbcrewMapper;
        this.tbcrewuserService = tbcrewuserService;
    }

    /**/
    public TbcrewushotDto.TbcrewushotAfterCreateDto create(TbcrewushotDto.TbcrewushotCreateDto params){
        return tbcrewushotRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbcrewushotDto.TbcrewushotAfterUpdateDto update(TbcrewushotDto.TbcrewushotUpdateDto params){
        Tbcrewushot tbcrewushot = tbcrewushotRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbcrewushot.setDeleted(params.getDeleted());
        }
        tbcrewushotRepository.save(tbcrewushot);
        return tbcrewushot.toAfterUpdateDto();
    }
    public TbcrewushotDto.TbcrewushotAfterUpdateDto delete(TbcrewushotDto.TbcrewushotUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbcrewushot tbcrewushot = tbcrewushotRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbcrewushotRepository.delete(tbcrewushot);
        return tbcrewushot.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbcrewushotDto.TbcrewushotUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbcrewushotDto.TbcrewushotSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbcrewushotDto.TbcrewushotSelectDto get(String id){
        return tbcrewushotMapper.detail(id);
    }
    public List<TbcrewushotDto.TbcrewushotSelectDto> list(TbcrewushotDto.TbcrewushotListDto params){
        return addListDetails(tbcrewushotMapper.list(params));
    }
    public List<TbcrewushotDto.TbcrewushotSelectDto> moreList(TbcrewushotDto.TbcrewushotMoreListDto params){
        params.afterBuild();
        return addListDetails(tbcrewushotMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbcrewushotDto.TbcrewushotSelectDto> pagedList(TbcrewushotDto.TbcrewushotPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbcrewushotMapper.pagedListCount(params)), addListDetails(tbcrewushotMapper.pagedList(params)));
    }

    public List<TbcrewushotDto.TbcrewushotSelectDto> addListDetails(List<TbcrewushotDto.TbcrewushotSelectDto> a_list){
        List<TbcrewushotDto.TbcrewushotSelectDto> result_list = new ArrayList<>();
        for(TbcrewushotDto.TbcrewushotSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
