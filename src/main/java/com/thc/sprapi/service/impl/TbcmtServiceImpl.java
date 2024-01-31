package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbcmt;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbboardDto;
import com.thc.sprapi.dto.TbcmtDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbcmtMapper;
import com.thc.sprapi.repository.TbcmtRepository;
import com.thc.sprapi.service.TbcmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbcmtServiceImpl implements TbcmtService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcmtRepository tbcmtRepository;
    private final TbcmtMapper tbcmtMapper;
    public TbcmtServiceImpl(
            TbcmtRepository tbcmtRepository
            ,TbcmtMapper tbcmtMapper
    ) {
        this.tbcmtRepository = tbcmtRepository;
        this.tbcmtMapper = tbcmtMapper;
    }

    public TbcmtDto.TbcmtAfterCreateDto create(TbcmtDto.TbcmtCreateDto params){
        return tbcmtRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbcmtDto.TbcmtAfterUpdateDto update(TbcmtDto.TbcmtUpdateDto params){
        Tbcmt tbcmt = tbcmtRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTbboardId() != null){
            tbcmt.setTbboardId(params.getTbboardId());
        }
        if(params.getContent() != null){
            tbcmt.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbcmt.setDeleted(params.getDeleted());
        }
        tbcmtRepository.save(tbcmt);
        return tbcmt.toAfterUpdateDto();
    }
    public TbcmtDto.TbcmtAfterUpdateDto delete(TbcmtDto.TbcmtUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }

    public TbcmtDto.TbcmtSelectDto detail(String id){
        return tbcmtMapper.detail(id);
    }
    public List<TbcmtDto.TbcmtSelectDto> list(TbcmtDto.TbcmtListDto params){
        return addListDetails(tbcmtMapper.list(params));
    }
    public List<TbcmtDto.TbcmtSelectDto> moreList(TbcmtDto.TbcmtMoreListDto params){
        params.afterBuild();
        return addListDetails(tbcmtMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbcmtDto.TbcmtSelectDto> pagedList(TbcmtDto.TbcmtPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbcmtMapper.pagedListCount(params)), addListDetails(tbcmtMapper.pagedList(params)));
    }

    public List<TbcmtDto.TbcmtSelectDto> addListDetails(List<TbcmtDto.TbcmtSelectDto> a_list){
        List<TbcmtDto.TbcmtSelectDto> result_list = new ArrayList<>();
        for(TbcmtDto.TbcmtSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
