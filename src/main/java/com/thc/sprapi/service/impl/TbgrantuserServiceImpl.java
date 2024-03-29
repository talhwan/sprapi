package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbgrantpart;
import com.thc.sprapi.domain.Tbgrantuser;
import com.thc.sprapi.domain.Tbgrantuser;
import com.thc.sprapi.domain.Tbgrantuser;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgrantuserMapper;
import com.thc.sprapi.repository.TbgrantuserRepository;
import com.thc.sprapi.service.TbgrantuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgrantuserServiceImpl implements TbgrantuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgrantuserRepository tbgrantuserRepository;
    private final TbgrantuserMapper tbgrantuserMapper;
    public TbgrantuserServiceImpl(
            TbgrantuserRepository tbgrantuserRepository
            ,TbgrantuserMapper tbgrantuserMapper
    ) {
        this.tbgrantuserRepository = tbgrantuserRepository;
        this.tbgrantuserMapper = tbgrantuserMapper;
    }

    public TbgrantuserDto.TbgrantuserAfterCreateDto toggle(TbgrantuserDto.TbgrantuserToggleDto params){
        TbgrantuserDto.TbgrantuserAfterCreateDto returnVal = null;
        Tbgrantuser tbgrantuser = tbgrantuserRepository.findByTbgrantIdAndTbuserId(params.getTbgrantId(), params.getTbuserId());

        if(params.isWay()){
            if(tbgrantuser == null){
                returnVal = tbgrantuserRepository.save(params.toEntity()).toAfterCreateDto();
            } else {
                returnVal = tbgrantuser.toAfterCreateDto();
            }
        } else {
            if(tbgrantuser == null){
            } else {
                delete(TbgrantuserDto.TbgrantuserUpdateDto.builder().id(tbgrantuser.getId()).build());
                returnVal = tbgrantuser.toAfterCreateDto();
            }
        }
        return returnVal;
    }
    public List<TbgrantuserDto.TbgrantuserSelectDto> moreListByTbgrantId(TbgrantuserDto.TbgrantuserMoreListDto params){
        params.afterBuild();
        return tbgrantuserMapper.moreListByTbgrantId(params);
    }
    /**/
    public TbgrantuserDto.TbgrantuserAfterCreateDto create(TbgrantuserDto.TbgrantuserCreateDto params){
        return tbgrantuserRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbgrantuserDto.TbgrantuserAfterUpdateDto update(TbgrantuserDto.TbgrantuserUpdateDto params){
        Tbgrantuser tbgrantuser = tbgrantuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbgrantuser.setDeleted(params.getDeleted());
        }
        tbgrantuserRepository.save(tbgrantuser);
        return tbgrantuser.toAfterUpdateDto();
    }
    public TbgrantuserDto.TbgrantuserAfterUpdateDto delete(TbgrantuserDto.TbgrantuserUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbgrantuser tbgrantuser = tbgrantuserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbgrantuserRepository.delete(tbgrantuser);
        return tbgrantuser.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbgrantuserDto.TbgrantuserUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgrantuserDto.TbgrantuserSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbgrantuserDto.TbgrantuserSelectDto get(String id){
        return tbgrantuserMapper.detail(id);
    }
    public List<TbgrantuserDto.TbgrantuserSelectDto> list(TbgrantuserDto.TbgrantuserListDto params){
        return addListDetails(tbgrantuserMapper.list(params));
    }
    public List<TbgrantuserDto.TbgrantuserSelectDto> moreList(TbgrantuserDto.TbgrantuserMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgrantuserMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgrantuserDto.TbgrantuserSelectDto> pagedList(TbgrantuserDto.TbgrantuserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgrantuserMapper.pagedListCount(params)), addListDetails(tbgrantuserMapper.pagedList(params)));
    }

    public List<TbgrantuserDto.TbgrantuserSelectDto> addListDetails(List<TbgrantuserDto.TbgrantuserSelectDto> a_list){
        List<TbgrantuserDto.TbgrantuserSelectDto> result_list = new ArrayList<>();
        for(TbgrantuserDto.TbgrantuserSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
