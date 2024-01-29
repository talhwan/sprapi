package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbpic;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbpicDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbpicMapper;
import com.thc.sprapi.repository.TbpicRepository;
import com.thc.sprapi.service.TbpicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbpicServiceImpl implements TbpicService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpicRepository tbpicRepository;
    private final TbpicMapper tbpicMapper;
    public TbpicServiceImpl(
            TbpicRepository tbpicRepository
            ,TbpicMapper tbpicMapper
    ) {
        this.tbpicRepository = tbpicRepository;
        this.tbpicMapper = tbpicMapper;
    }

    public TbpicDto.TbpicAfterCreateDto create(TbpicDto.TbpicCreateDto params){
        return tbpicRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbpicDto.TbpicAfterUpdateDto update(TbpicDto.TbpicUpdateDto params){
        Tbpic tbpic = tbpicRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTbboardId() != null){
            tbpic.setTbboardId(params.getTbboardId());
        }
        if(params.getContent() != null){
            tbpic.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbpic.setDeleted(params.getDeleted());
        }
        tbpicRepository.save(tbpic);
        return tbpic.toAfterUpdateDto();
    }
    public TbpicDto.TbpicAfterUpdateDto delete(TbpicDto.TbpicUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }

    public TbpicDto.TbpicSelectDto detail(String id){
        return tbpicMapper.detail(id);
    }
    public List<TbpicDto.TbpicSelectDto> list(TbpicDto.TbpicListDto params){
        return tbpicMapper.list(params);
    }
    public List<TbpicDto.TbpicSelectDto> moreList(TbpicDto.TbpicMoreListDto params){
        params.afterBuild();
        return tbpicMapper.moreList(params);
    }
    public CommonAfterPagedListDto<TbpicDto.TbpicSelectDto> pagedList(TbpicDto.TbpicPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbpicMapper.pagedListCount(params)), tbpicMapper.pagedList(params));
    }

}
