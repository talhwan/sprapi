package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbnotice;
import com.thc.sprapi.dto.*;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbnoticeMapper;
import com.thc.sprapi.repository.TbnoticeRepository;
import com.thc.sprapi.service.TbnoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbnoticeServiceImpl implements TbnoticeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbnoticeRepository tbnoticeRepository;
    private final TbnoticeMapper tbnoticeMapper;
    public TbnoticeServiceImpl(
            TbnoticeRepository tbnoticeRepository
            ,TbnoticeMapper tbnoticeMapper
    ) {
        this.tbnoticeRepository = tbnoticeRepository;
        this.tbnoticeMapper = tbnoticeMapper;
    }

    public TbnoticeDto.TbnoticeAfterCreateDto create(TbnoticeDto.TbnoticeCreateDto params){
        return tbnoticeRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbnoticeDto.TbnoticeAfterUpdateDto update(TbnoticeDto.TbnoticeUpdateDto params){
        Tbnotice tbnotice = tbnoticeRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbnotice.setTitle(params.getTitle());
        }
        if(params.getCate() != null){
            tbnotice.setCate(params.getCate());
        }
        if(params.getMpic() != null){
            tbnotice.setMpic(params.getMpic());
        }
        if(params.getContent() != null){
            tbnotice.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbnotice.setDeleted(params.getDeleted());
        }
        tbnoticeRepository.save(tbnotice);
        return tbnotice.toAfterUpdateDto();
    }
    public TbnoticeDto.TbnoticeAfterUpdateDto delete(TbnoticeDto.TbnoticeUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbnoticeDto.TbnoticeUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbnoticeDto.TbnoticeSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbnoticeDto.TbnoticeSelectDto get(String id){
        return tbnoticeMapper.detail(id);
    }
    public List<TbnoticeDto.TbnoticeSelectDto> list(TbnoticeDto.TbnoticeListDto params){
        return addListDetails(tbnoticeMapper.list(params));
    }
    public List<TbnoticeDto.TbnoticeSelectDto> moreList(TbnoticeDto.TbnoticeMoreListDto params){
        params.afterBuild();
        return addListDetails(tbnoticeMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbnoticeDto.TbnoticeSelectDto> pagedList(TbnoticeDto.TbnoticePagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbnoticeMapper.pagedListCount(params)), addListDetails(tbnoticeMapper.pagedList(params)));
    }

    public List<TbnoticeDto.TbnoticeSelectDto> addListDetails(List<TbnoticeDto.TbnoticeSelectDto> a_list){
        List<TbnoticeDto.TbnoticeSelectDto> result_list = new ArrayList<>();
        for(TbnoticeDto.TbnoticeSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
