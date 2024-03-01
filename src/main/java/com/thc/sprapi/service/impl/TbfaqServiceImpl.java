package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbfaq;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbfaqDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbfaqMapper;
import com.thc.sprapi.repository.TbfaqRepository;
import com.thc.sprapi.service.TbfaqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfaqServiceImpl implements TbfaqService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbfaqRepository tbfaqRepository;
    private final TbfaqMapper tbfaqMapper;
    public TbfaqServiceImpl(
            TbfaqRepository tbfaqRepository
            ,TbfaqMapper tbfaqMapper
    ) {
        this.tbfaqRepository = tbfaqRepository;
        this.tbfaqMapper = tbfaqMapper;
    }

    public TbfaqDto.TbfaqAfterCreateDto create(TbfaqDto.TbfaqCreateDto params){
        return tbfaqRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbfaqDto.TbfaqAfterUpdateDto update(TbfaqDto.TbfaqUpdateDto params){
        Tbfaq tbfaq = tbfaqRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbfaq.setTitle(params.getTitle());
        }
        if(params.getCate() != null){
            tbfaq.setCate(params.getCate());
        }
        if(params.getContent() != null){
            tbfaq.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbfaq.setDeleted(params.getDeleted());
        }
        tbfaqRepository.save(tbfaq);
        return tbfaq.toAfterUpdateDto();
    }
    public TbfaqDto.TbfaqAfterUpdateDto delete(TbfaqDto.TbfaqUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbfaqDto.TbfaqUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbfaqDto.TbfaqSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbfaqDto.TbfaqSelectDto get(String id){
        return tbfaqMapper.detail(id);
    }
    public List<TbfaqDto.TbfaqSelectDto> list(TbfaqDto.TbfaqListDto params){
        return addListDetails(tbfaqMapper.list(params));
    }
    public List<TbfaqDto.TbfaqSelectDto> moreList(TbfaqDto.TbfaqMoreListDto params){
        params.afterBuild();
        return addListDetails(tbfaqMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbfaqDto.TbfaqSelectDto> pagedList(TbfaqDto.TbfaqPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbfaqMapper.pagedListCount(params)), addListDetails(tbfaqMapper.pagedList(params)));
    }

    public List<TbfaqDto.TbfaqSelectDto> addListDetails(List<TbfaqDto.TbfaqSelectDto> a_list){
        List<TbfaqDto.TbfaqSelectDto> result_list = new ArrayList<>();
        for(TbfaqDto.TbfaqSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
