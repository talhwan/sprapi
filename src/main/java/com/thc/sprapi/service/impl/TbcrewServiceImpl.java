package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbcrew;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbcrewMapper;
import com.thc.sprapi.repository.TbcrewRepository;
import com.thc.sprapi.service.TbcrewService;
import com.thc.sprapi.service.TbcrewuserService;
import com.thc.sprapi.service.TbcrewushotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbcrewServiceImpl implements TbcrewService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcrewRepository tbcrewRepository;
    private final TbcrewMapper tbcrewMapper;
    private final TbcrewuserService tbcrewuserService;
    private final TbcrewushotService tbcrewushotService;
    public TbcrewServiceImpl(
            TbcrewRepository tbcrewRepository
            , TbcrewMapper tbcrewMapper
            , TbcrewuserService tbcrewuserService
            , TbcrewushotService tbcrewushotService
    ) {
        this.tbcrewRepository = tbcrewRepository;
        this.tbcrewMapper = tbcrewMapper;
        this.tbcrewuserService = tbcrewuserService;
        this.tbcrewushotService = tbcrewushotService;
    }


    /**/

    public TbcrewDto.TbcrewAfterCreateDto create(TbcrewDto.TbcrewCreateDto params){
        TbcrewDto.TbcrewAfterCreateDto result = tbcrewRepository.save(params.toEntity()).toAfterCreateDto();
        return result;
    }
    public TbcrewDto.TbcrewAfterUpdateDto update(TbcrewDto.TbcrewUpdateDto params){
        Tbcrew tbcrew = tbcrewRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbcrew.setDeleted(params.getDeleted());
        }
        if(params.getTitle() != null){
            tbcrew.setTitle(params.getTitle());
        }
        if(params.getProcess() != null){
            tbcrew.setProcess(params.getProcess());
        }
        if(params.getCate() != null){
            tbcrew.setCate(params.getCate());
        }
        if(params.getMpic() != null){
            tbcrew.setMpic(params.getMpic());
        }
        if(params.getContent() != null){
            tbcrew.setContent(params.getContent());
        }
        tbcrewRepository.save(tbcrew);
        return tbcrew.toAfterUpdateDto();
    }
    public TbcrewDto.TbcrewAfterUpdateDto delete(TbcrewDto.TbcrewUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbcrewDto.TbcrewUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbcrewDto.TbcrewSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbcrewDto.TbcrewSelectDto get(String id){
        return tbcrewMapper.detail(id);
    }
    public List<TbcrewDto.TbcrewSelectDto> list(TbcrewDto.TbcrewListDto params){
        return addListDetails(tbcrewMapper.list(params));
    }
    public List<TbcrewDto.TbcrewSelectDto> moreList(TbcrewDto.TbcrewMoreListDto params){
        params.afterBuild();
        return addListDetails(tbcrewMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbcrewDto.TbcrewSelectDto> pagedList(TbcrewDto.TbcrewPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbcrewMapper.pagedListCount(params)), addListDetails(tbcrewMapper.pagedList(params)));
    }

    public List<TbcrewDto.TbcrewSelectDto> addListDetails(List<TbcrewDto.TbcrewSelectDto> a_list){
        List<TbcrewDto.TbcrewSelectDto> result_list = new ArrayList<>();
        for(TbcrewDto.TbcrewSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
