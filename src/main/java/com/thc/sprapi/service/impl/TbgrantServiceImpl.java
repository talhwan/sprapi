package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbgrant;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantDto;
import com.thc.sprapi.dto.TbgrantpartDto;
import com.thc.sprapi.exception.NoAccessGrantException;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgrantMapper;
import com.thc.sprapi.repository.TbgrantRepository;
import com.thc.sprapi.service.TbgrantService;
import com.thc.sprapi.service.TbgrantpartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgrantServiceImpl implements TbgrantService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantRepository tbgrantRepository;
    private final TbgrantMapper tbgrantMapper;
    private final TbgrantpartService tbgrantpartService;
    public TbgrantServiceImpl(
            TbgrantRepository tbgrantRepository
            ,TbgrantMapper tbgrantMapper
            ,TbgrantpartService tbgrantpartService
    ) {
        this.tbgrantRepository = tbgrantRepository;
        this.tbgrantMapper = tbgrantMapper;
        this.tbgrantpartService = tbgrantpartService;
    }

    public boolean access(TbgrantDto.TbgrantAccessDto params){
        TbgrantDto.TbgrantSelectDto result = tbgrantMapper.access(params);
        /*
        return false;
         */
        if(result == null){
            if(params.isBeAdmin()){
                throw new NoAccessGrantException("");
            }
        }
        return params.isBeAdmin();
    }
    public TbgrantDto.TbgrantAfterCreateDto create(TbgrantDto.TbgrantCreateDto params){
        TbgrantDto.TbgrantAfterCreateDto result = tbgrantRepository.save(params.toEntity()).toAfterCreateDto();
        return result;
    }
    public TbgrantDto.TbgrantAfterUpdateDto update(TbgrantDto.TbgrantUpdateDto params){
        Tbgrant tbgrant = tbgrantRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbgrant.setTitle(params.getTitle());
        }
        if(params.getContent() != null){
            tbgrant.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbgrant.setDeleted(params.getDeleted());
        }
        tbgrantRepository.save(tbgrant);
        return tbgrant.toAfterUpdateDto();
    }
    public TbgrantDto.TbgrantAfterUpdateDto delete(TbgrantDto.TbgrantUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            /*
            TbgrantDto.TbgrantUpdateDto aa = new TbgrantDto.TbgrantUpdateDto();
            aa.setId(each);
            delete(aa);
             */
            delete(TbgrantDto.TbgrantUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgrantDto.TbgrantSelectDto detail(String id){
        TbgrantDto.TbgrantSelectDto result = tbgrantMapper.detail(id);
        result.setTbgrantparts(tbgrantpartService.list(TbgrantpartDto.TbgrantpartListDto.builder().tbgrantId(id).build()));
        return result;
    }
    public List<TbgrantDto.TbgrantSelectDto> list(TbgrantDto.TbgrantListDto params){
        return addListDetails(tbgrantMapper.list(params));
    }
    public List<TbgrantDto.TbgrantSelectDto> moreList(TbgrantDto.TbgrantMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgrantMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgrantDto.TbgrantSelectDto> pagedList(TbgrantDto.TbgrantPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgrantMapper.pagedListCount(params)), addListDetails(tbgrantMapper.pagedList(params)));
    }

    public List<TbgrantDto.TbgrantSelectDto> addListDetails(List<TbgrantDto.TbgrantSelectDto> a_list){
        List<TbgrantDto.TbgrantSelectDto> result_list = new ArrayList<>();
        for(TbgrantDto.TbgrantSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
