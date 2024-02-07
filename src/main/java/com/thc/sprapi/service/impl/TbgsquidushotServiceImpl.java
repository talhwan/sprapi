package com.thc.sprapi.service.impl;

import com.thc.sprapi.controller.socket.TbgsquidWsHandler;
import com.thc.sprapi.domain.Tbgsquiduser;
import com.thc.sprapi.domain.Tbgsquidushot;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgsquidMapper;
import com.thc.sprapi.mapper.TbgsquidushotMapper;
import com.thc.sprapi.repository.TbgsquidushotRepository;
import com.thc.sprapi.service.TbgsquidService;
import com.thc.sprapi.service.TbgsquiduserService;
import com.thc.sprapi.service.TbgsquidushotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbgsquidushotServiceImpl implements TbgsquidushotService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgsquidushotRepository tbgsquidushotRepository;
    private final TbgsquidushotMapper tbgsquidushotMapper;
    private final TbgsquidMapper tbgsquidMapper;
    private final TbgsquiduserService tbgsquiduserService;
    private final TbgsquidWsHandler tbgsquidWsHandler;
    public TbgsquidushotServiceImpl(
            TbgsquidushotRepository tbgsquidushotRepository
            , TbgsquidushotMapper tbgsquidushotMapper
            , TbgsquidMapper tbgsquidMapper
            , TbgsquiduserService tbgsquiduserService
            , TbgsquidWsHandler tbgsquidWsHandler
    ) {
        this.tbgsquidushotRepository = tbgsquidushotRepository;
        this.tbgsquidushotMapper = tbgsquidushotMapper;
        this.tbgsquidMapper = tbgsquidMapper;
        this.tbgsquiduserService = tbgsquiduserService;
        this.tbgsquidWsHandler = tbgsquidWsHandler;
    }

    public List<TbgsquidushotDto.TbgsquidushotSelectDto> next(TbgsquidushotDto.TbgsquidushotListDto params){
        return tbgsquidushotMapper.next(params);
    }
    public TbgsquidushotDto.TbgsquidushotAfterCreateDto join(TbgsquidushotDto.TbgsquidushotCreateDto params){

        TbgsquiduserDto.TbgsquiduserSelectDto tbgsquiduser = tbgsquiduserService.detail(params.getTbgsquiduserId());
        TbgsquidDto.TbgsquidSelectDto tbgsquid = tbgsquidMapper.detail(tbgsquiduser.getTbgsquidId());
        if(tbgsquiduser.getProcess().equals(tbgsquid.getProcess())){
        } else {
            throw new NoMatchingDataException("");
        }

        Tbgsquidushot tbgsquidushot = tbgsquidushotRepository.findByTbgsquiduserIdAndLevel(params.getTbgsquiduserId(), params.getLevel());
        if(tbgsquidushot == null){
            tbgsquidushot = tbgsquidushotRepository.save(params.toEntity());
        } else {
            tbgsquidushot.setContent(params.getContent());
            tbgsquidushotRepository.save(tbgsquidushot);
        }

        try{
            TbgsquiduserDto.TbgsquiduserUpdateDto updateParams = TbgsquiduserDto.TbgsquiduserUpdateDto.builder().id(tbgsquiduser.getId()).content(params.getContent()).build();
            tbgsquiduserService.update(updateParams);
            Map<String, Object> map_message = new HashMap<>();
            map_message.put("ws_type", "selected");
            map_message.put("tbgsquidId", tbgsquiduser.getTbgsquidId());
            map_message.put("tbgsquiduserId", tbgsquiduser.getId());
            map_message.put("tbgsquiduserContent", params.getContent());
            tbgsquidWsHandler.sendByTbgsquid(map_message);
        } catch (Exception e){
        }
        return tbgsquidushot.toAfterCreateDto();
    }

    /**/
    public TbgsquidushotDto.TbgsquidushotAfterCreateDto create(TbgsquidushotDto.TbgsquidushotCreateDto params){
        return tbgsquidushotRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbgsquidushotDto.TbgsquidushotAfterUpdateDto update(TbgsquidushotDto.TbgsquidushotUpdateDto params){
        Tbgsquidushot tbgsquidushot = tbgsquidushotRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbgsquidushot.setDeleted(params.getDeleted());
        }
        tbgsquidushotRepository.save(tbgsquidushot);
        return tbgsquidushot.toAfterUpdateDto();
    }
    public TbgsquidushotDto.TbgsquidushotAfterUpdateDto delete(TbgsquidushotDto.TbgsquidushotUpdateDto params){
        /*params.setDeleted("Y");
        return update(params);*/
        Tbgsquidushot tbgsquidushot = tbgsquidushotRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        tbgsquidushotRepository.delete(tbgsquidushot);
        return tbgsquidushot.toAfterUpdateDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbgsquidushotDto.TbgsquidushotUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgsquidushotDto.TbgsquidushotSelectDto detail(String id){
        return tbgsquidushotMapper.detail(id);
    }
    public List<TbgsquidushotDto.TbgsquidushotSelectDto> list(TbgsquidushotDto.TbgsquidushotListDto params){
        return addListDetails(tbgsquidushotMapper.list(params));
    }
    public List<TbgsquidushotDto.TbgsquidushotSelectDto> moreList(TbgsquidushotDto.TbgsquidushotMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgsquidushotMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgsquidushotDto.TbgsquidushotSelectDto> pagedList(TbgsquidushotDto.TbgsquidushotPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgsquidushotMapper.pagedListCount(params)), addListDetails(tbgsquidushotMapper.pagedList(params)));
    }

    public List<TbgsquidushotDto.TbgsquidushotSelectDto> addListDetails(List<TbgsquidushotDto.TbgsquidushotSelectDto> a_list){
        List<TbgsquidushotDto.TbgsquidushotSelectDto> result_list = new ArrayList<>();
        for(TbgsquidushotDto.TbgsquidushotSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
