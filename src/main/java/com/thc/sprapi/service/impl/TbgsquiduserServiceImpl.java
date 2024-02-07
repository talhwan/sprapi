package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbgsquiduser;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgsquiduserDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgsquiduserMapper;
import com.thc.sprapi.repository.TbgsquiduserRepository;
import com.thc.sprapi.service.TbgsquiduserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgsquiduserServiceImpl implements TbgsquiduserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgsquiduserRepository tbgsquiduserRepository;
    private final TbgsquiduserMapper tbgsquiduserMapper;
    public TbgsquiduserServiceImpl(
            TbgsquiduserRepository tbgsquiduserRepository
            ,TbgsquiduserMapper tbgsquiduserMapper
    ) {
        this.tbgsquiduserRepository = tbgsquiduserRepository;
        this.tbgsquiduserMapper = tbgsquiduserMapper;
    }

    public List<TbgsquiduserDto.TbgsquiduserSelectDto> listByUnderProcess(TbgsquiduserDto.TbgsquiduserListDto params){
        return tbgsquiduserMapper.listByUnderProcess(params);
    }
    public TbgsquiduserDto.TbgsquiduserAfterCreateDto join(TbgsquiduserDto.TbgsquiduserCreateDto params){
        Tbgsquiduser tbgsquiduser = tbgsquiduserRepository.findByTbgsquidIdAndTbuserId(params.getTbgsquidId(), params.getTbuserId());
        if(tbgsquiduser == null){
            tbgsquiduser = tbgsquiduserRepository.save(params.toEntity());
        }
        return tbgsquiduser.toAfterCreateDto();
    }

    /**/
    public TbgsquiduserDto.TbgsquiduserAfterCreateDto create(TbgsquiduserDto.TbgsquiduserCreateDto params){
        return tbgsquiduserRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbgsquiduserDto.TbgsquiduserAfterUpdateDto update(TbgsquiduserDto.TbgsquiduserUpdateDto params){
        Tbgsquiduser tbgsquiduser = tbgsquiduserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbgsquiduser.setDeleted(params.getDeleted());
        }
        if(params.getProcess() != null){
            tbgsquiduser.setProcess(params.getProcess());
        }
        if(params.getContent() != null){
            tbgsquiduser.setContent(params.getContent());
        }
        tbgsquiduserRepository.save(tbgsquiduser);
        return tbgsquiduser.toAfterUpdateDto();
    }
    public TbgsquiduserDto.TbgsquiduserAfterUpdateDto delete(TbgsquiduserDto.TbgsquiduserUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbgsquiduser tbgsquiduser = tbgsquiduserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbgsquiduserRepository.delete(tbgsquiduser);
        return tbgsquiduser.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbgsquiduserDto.TbgsquiduserUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgsquiduserDto.TbgsquiduserSelectDto detail(String id){
        return tbgsquiduserMapper.detail(id);
    }
    public List<TbgsquiduserDto.TbgsquiduserSelectDto> list(TbgsquiduserDto.TbgsquiduserListDto params){
        return addListDetails(tbgsquiduserMapper.list(params));
    }
    public List<TbgsquiduserDto.TbgsquiduserSelectDto> moreList(TbgsquiduserDto.TbgsquiduserMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgsquiduserMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgsquiduserDto.TbgsquiduserSelectDto> pagedList(TbgsquiduserDto.TbgsquiduserPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgsquiduserMapper.pagedListCount(params)), addListDetails(tbgsquiduserMapper.pagedList(params)));
    }

    public List<TbgsquiduserDto.TbgsquiduserSelectDto> addListDetails(List<TbgsquiduserDto.TbgsquiduserSelectDto> a_list){
        List<TbgsquiduserDto.TbgsquiduserSelectDto> result_list = new ArrayList<>();
        for(TbgsquiduserDto.TbgsquiduserSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
