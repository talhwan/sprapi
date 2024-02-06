package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbgrantpart;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantpartDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgrantpartMapper;
import com.thc.sprapi.repository.TbgrantpartRepository;
import com.thc.sprapi.service.TbgrantpartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TbgrantpartServiceImpl implements TbgrantpartService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantpartRepository tbgrantpartRepository;
    private final TbgrantpartMapper tbgrantpartMapper;
    public TbgrantpartServiceImpl(
            TbgrantpartRepository tbgrantpartRepository
            ,TbgrantpartMapper tbgrantpartMapper
    ) {
        this.tbgrantpartRepository = tbgrantpartRepository;
        this.tbgrantpartMapper = tbgrantpartMapper;
    }

    public TbgrantpartDto.TbgrantpartAfterCreateDto toggle(TbgrantpartDto.TbgrantpartToggleDto params){
        TbgrantpartDto.TbgrantpartAfterCreateDto returnVal = null;
        Tbgrantpart tbgrantpart = tbgrantpartRepository.findByTbgrantIdAndTypeAndContent(params.getTbgrantId(), params.getType(), params.getContent());
        if(params.isWay()){
            if(tbgrantpart == null){
                returnVal = tbgrantpartRepository.save(params.toEntity()).toAfterCreateDto();
            } else {
                returnVal = tbgrantpart.toAfterCreateDto();
            }
        } else {
            if(tbgrantpart == null){
            } else {
                delete(TbgrantpartDto.TbgrantpartUpdateDto.builder().id(tbgrantpart.getId()).build());
                returnVal = tbgrantpart.toAfterCreateDto();
            }

        }
        return returnVal;
    }
    public TbgrantpartDto.TbgrantpartAfterCreateDto create(TbgrantpartDto.TbgrantpartCreateDto params){
        return tbgrantpartRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbgrantpartDto.TbgrantpartAfterUpdateDto update(TbgrantpartDto.TbgrantpartUpdateDto params){
        Tbgrantpart tbgrantpart = tbgrantpartRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTbgrantId() != null){
            tbgrantpart.setTbgrantId(params.getTbgrantId());
        }
        if(params.getContent() != null){
            tbgrantpart.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbgrantpart.setDeleted(params.getDeleted());
        }
        tbgrantpartRepository.save(tbgrantpart);
        return tbgrantpart.toAfterUpdateDto();
    }
    public TbgrantpartDto.TbgrantpartAfterUpdateDto delete(TbgrantpartDto.TbgrantpartUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbgrantpart tbgrantpart = tbgrantpartRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbgrantpartRepository.delete(tbgrantpart);
        return tbgrantpart.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbgrantpartDto.TbgrantpartUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgrantpartDto.TbgrantpartSelectDto detail(String id){
        return tbgrantpartMapper.detail(id);
    }
    public List<TbgrantpartDto.TbgrantpartSelectDto> list(TbgrantpartDto.TbgrantpartListDto params){
        return addListDetails(tbgrantpartMapper.list(params));
    }
    public List<TbgrantpartDto.TbgrantpartSelectDto> moreList(TbgrantpartDto.TbgrantpartMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgrantpartMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgrantpartDto.TbgrantpartSelectDto> pagedList(TbgrantpartDto.TbgrantpartPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgrantpartMapper.pagedListCount(params)), addListDetails(tbgrantpartMapper.pagedList(params)));
    }

    public List<TbgrantpartDto.TbgrantpartSelectDto> addListDetails(List<TbgrantpartDto.TbgrantpartSelectDto> a_list){
        List<TbgrantpartDto.TbgrantpartSelectDto> result_list = new ArrayList<>();
        for(TbgrantpartDto.TbgrantpartSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
