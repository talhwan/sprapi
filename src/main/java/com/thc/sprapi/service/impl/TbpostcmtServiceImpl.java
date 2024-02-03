package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbpostcmt;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbpostcmtDto;
import com.thc.sprapi.dto.TbpostcmtDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbpostcmtMapper;
import com.thc.sprapi.repository.TbpostcmtRepository;
import com.thc.sprapi.service.TbpostcmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostcmtServiceImpl implements TbpostcmtService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostcmtRepository tbpostcmtRepository;
    private final TbpostcmtMapper tbpostcmtMapper;
    public TbpostcmtServiceImpl(
            TbpostcmtRepository tbpostcmtRepository
            ,TbpostcmtMapper tbpostcmtMapper
    ) {
        this.tbpostcmtRepository = tbpostcmtRepository;
        this.tbpostcmtMapper = tbpostcmtMapper;
    }

    public TbpostcmtDto.TbpostcmtAfterCreateDto create(TbpostcmtDto.TbpostcmtCreateDto params){
        return tbpostcmtRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbpostcmtDto.TbpostcmtAfterUpdateDto update(TbpostcmtDto.TbpostcmtUpdateDto params){
        Tbpostcmt tbpostcmt = tbpostcmtRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTbpostId() != null){
            tbpostcmt.setTbpostId(params.getTbpostId());
        }
        if(params.getContent() != null){
            tbpostcmt.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbpostcmt.setDeleted(params.getDeleted());
        }
        tbpostcmtRepository.save(tbpostcmt);
        return tbpostcmt.toAfterUpdateDto();
    }
    public TbpostcmtDto.TbpostcmtAfterUpdateDto delete(TbpostcmtDto.TbpostcmtUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbpostcmtDto.TbpostcmtUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbpostcmtDto.TbpostcmtSelectDto detail(String id){
        return tbpostcmtMapper.detail(id);
    }
    public List<TbpostcmtDto.TbpostcmtSelectDto> list(TbpostcmtDto.TbpostcmtListDto params){
        return addListDetails(tbpostcmtMapper.list(params));
    }
    public List<TbpostcmtDto.TbpostcmtSelectDto> moreList(TbpostcmtDto.TbpostcmtMoreListDto params){
        params.afterBuild();
        return addListDetails(tbpostcmtMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbpostcmtDto.TbpostcmtSelectDto> pagedList(TbpostcmtDto.TbpostcmtPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbpostcmtMapper.pagedListCount(params)), addListDetails(tbpostcmtMapper.pagedList(params)));
    }

    public List<TbpostcmtDto.TbpostcmtSelectDto> addListDetails(List<TbpostcmtDto.TbpostcmtSelectDto> a_list){
        List<TbpostcmtDto.TbpostcmtSelectDto> result_list = new ArrayList<>();
        for(TbpostcmtDto.TbpostcmtSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
