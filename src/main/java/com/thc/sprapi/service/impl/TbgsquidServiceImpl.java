package com.thc.sprapi.service.impl;

import com.thc.sprapi.controller.socket.TbgsquidWsHandler;
import com.thc.sprapi.domain.Tbgsquid;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbgsquidMapper;
import com.thc.sprapi.repository.TbgsquidRepository;
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
public class TbgsquidServiceImpl implements TbgsquidService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbgsquidRepository tbgsquidRepository;
    private final TbgsquidMapper tbgsquidMapper;
    private final TbgsquiduserService tbgsquiduserService;
    private final TbgsquidushotService tbgsquidushotService;
    private final TbgsquidWsHandler tbgsquidWsHandler;
    public TbgsquidServiceImpl(
            TbgsquidRepository tbgsquidRepository
            , TbgsquidMapper tbgsquidMapper
            , TbgsquiduserService tbgsquiduserService
            , TbgsquidushotService tbgsquidushotService
            , TbgsquidWsHandler tbgsquidWsHandler
    ) {
        this.tbgsquidRepository = tbgsquidRepository;
        this.tbgsquidMapper = tbgsquidMapper;
        this.tbgsquiduserService = tbgsquiduserService;
        this.tbgsquidushotService = tbgsquidushotService;
        this.tbgsquidWsHandler = tbgsquidWsHandler;
    }

    public TbgsquidDto.TbgsquidAfterUpdateDto next(TbgsquidDto.TbgsquidUpdateDto params){
        Tbgsquid tbgsquid = tbgsquidRepository.findById(params.getId()).orElseThrow(() -> new NoMatchingDataException(""));
        int nowInt = Integer.parseInt(tbgsquid.getProcess());
        int nextInt = (nowInt + 1);
        update(TbgsquidDto.TbgsquidUpdateDto.builder().id(params.getId()).process(nextInt + "").build());

        int nextAlive = 0;
        List<TbgsquidushotDto.TbgsquidushotSelectDto> nextList = tbgsquidushotService.next(TbgsquidushotDto.TbgsquidushotListDto.builder().level(nowInt + "").build());
        //tbgsquiduserService.listByUnderProcess(TbgsquiduserDto.TbgsquiduserListDto.builder().process(nextInt + "").build());
        for(TbgsquidushotDto.TbgsquidushotSelectDto each : nextList){
            tbgsquiduserService.update(TbgsquiduserDto.TbgsquiduserUpdateDto.builder().id(each.getTbgsquiduserId()).process(nextInt + "").build());
        }

        try{
            Map<String, Object> map_message = new HashMap<>();
            map_message.put("ws_type", "next");
            map_message.put("tbgsquidId", params.getId());
            map_message.put("tbgsquidProcess", nextInt);
            map_message.put("nextList", nextList.size());
            tbgsquidWsHandler.sendByTbgsquid(map_message);
        } catch (Exception e){
        }

        return tbgsquid.toAfterUpdateDto();
    }

    /**/

    public TbgsquidDto.TbgsquidAfterCreateDto create(TbgsquidDto.TbgsquidCreateDto params){
        TbgsquidDto.TbgsquidAfterCreateDto result = tbgsquidRepository.save(params.toEntity()).toAfterCreateDto();
        return result;
    }
    public TbgsquidDto.TbgsquidAfterUpdateDto update(TbgsquidDto.TbgsquidUpdateDto params){
        Tbgsquid tbgsquid = tbgsquidRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbgsquid.setTitle(params.getTitle());
        }
        if(params.getProcess() != null){
            tbgsquid.setProcess(params.getProcess());
        }
        if(params.getStartdate() != null){
            tbgsquid.setStartdate(params.getStartdate());
        }
        if(params.getStarttime() != null){
            tbgsquid.setStarttime(params.getStarttime());
        }
        if(params.getGoal() != null){
            tbgsquid.setGoal(Integer.parseInt(params.getGoal()));
        }
        if(params.getContent() != null){
            tbgsquid.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbgsquid.setDeleted(params.getDeleted());
        }
        tbgsquidRepository.save(tbgsquid);
        return tbgsquid.toAfterUpdateDto();
    }
    public TbgsquidDto.TbgsquidAfterUpdateDto delete(TbgsquidDto.TbgsquidUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbgsquidDto.TbgsquidUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbgsquidDto.TbgsquidSelectDto detail(String id){
        TbgsquidDto.TbgsquidSelectDto result = tbgsquidMapper.detail(id);
        return result;
    }
    public List<TbgsquidDto.TbgsquidSelectDto> list(TbgsquidDto.TbgsquidListDto params){
        return addListDetails(tbgsquidMapper.list(params));
    }
    public List<TbgsquidDto.TbgsquidSelectDto> moreList(TbgsquidDto.TbgsquidMoreListDto params){
        params.afterBuild();
        return addListDetails(tbgsquidMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbgsquidDto.TbgsquidSelectDto> pagedList(TbgsquidDto.TbgsquidPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbgsquidMapper.pagedListCount(params)), addListDetails(tbgsquidMapper.pagedList(params)));
    }

    public List<TbgsquidDto.TbgsquidSelectDto> addListDetails(List<TbgsquidDto.TbgsquidSelectDto> a_list){
        List<TbgsquidDto.TbgsquidSelectDto> result_list = new ArrayList<>();
        for(TbgsquidDto.TbgsquidSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
    }

}
