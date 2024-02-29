package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbpostfile;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.dto.TbpostfileDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbpostfileMapper;
import com.thc.sprapi.repository.TbpostfileRepository;
import com.thc.sprapi.service.TbpostfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostfileServiceImpl implements TbpostfileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostfileRepository tbpostfileRepository;
    private final TbpostfileMapper tbpostfileMapper;
    public TbpostfileServiceImpl(
            TbpostfileRepository tbpostfileRepository
            ,TbpostfileMapper tbpostfileMapper
    ) {
        this.tbpostfileRepository = tbpostfileRepository;
        this.tbpostfileMapper = tbpostfileMapper;
    }

    public TbpostfileDto.TbpostfileAfterCreateDto create(TbpostfileDto.TbpostfileCreateDto params){
        return tbpostfileRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbpostfileDto.TbpostfileAfterUpdateDto update(TbpostfileDto.TbpostfileUpdateDto params){
        Tbpostfile tbpostfile = tbpostfileRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTbpostId() != null){
            tbpostfile.setTbpostId(params.getTbpostId());
        }
        if(params.getContent() != null){
            tbpostfile.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbpostfile.setDeleted(params.getDeleted());
        }
        tbpostfileRepository.save(tbpostfile);
        return tbpostfile.toAfterUpdateDto();
    }
    public TbpostfileDto.TbpostfileAfterUpdateDto delete(TbpostfileDto.TbpostfileUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbpostfileDto.TbpostfileUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbpostfileDto.TbpostfileSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbpostfileDto.TbpostfileSelectDto get(String id){
        return tbpostfileMapper.detail(id);
    }
    public List<TbpostfileDto.TbpostfileSelectDto> list(TbpostfileDto.TbpostfileListDto params){
        return addListDetails(tbpostfileMapper.list(params));
    }
    public List<TbpostfileDto.TbpostfileSelectDto> moreList(TbpostfileDto.TbpostfileMoreListDto params){
        params.afterBuild();
        return addListDetails(tbpostfileMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbpostfileDto.TbpostfileSelectDto> pagedList(TbpostfileDto.TbpostfilePagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbpostfileMapper.pagedListCount(params)), addListDetails(tbpostfileMapper.pagedList(params)));
    }

    public List<TbpostfileDto.TbpostfileSelectDto> addListDetails(List<TbpostfileDto.TbpostfileSelectDto> a_list){
        List<TbpostfileDto.TbpostfileSelectDto> result_list = new ArrayList<>();
        for(TbpostfileDto.TbpostfileSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
