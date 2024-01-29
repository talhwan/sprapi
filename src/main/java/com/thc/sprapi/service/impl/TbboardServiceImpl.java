package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbboard;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbboardDto;
import com.thc.sprapi.dto.TbpicDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbboardMapper;
import com.thc.sprapi.repository.TbboardRepository;
import com.thc.sprapi.service.TbboardService;
import com.thc.sprapi.service.TbpicService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbboardServiceImpl implements TbboardService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbboardRepository tbboardRepository;
    private final TbboardMapper tbboardMapper;
    private final TbpicService tbpicService;
    public TbboardServiceImpl(
            TbboardRepository tbboardRepository
            ,TbboardMapper tbboardMapper
            ,TbpicService tbpicService
    ) {
        this.tbboardRepository = tbboardRepository;
        this.tbboardMapper = tbboardMapper;
        this.tbpicService = tbpicService;
    }

    public TbboardDto.TbboardAfterCreateDto create(TbboardDto.TbboardCreateDto params){
        TbboardDto.TbboardAfterCreateDto result = tbboardRepository.save(params.toEntity()).toAfterCreateDto();
        String[] pics = params.getPics();
        String[] types = params.getTypes();
        for(int i=0;i<pics.length;i++){
            tbpicService.create(new TbpicDto.TbpicCreateDto(result.getId(), pics[i], types[i]));
        }
        /*
        for (String pic : params.getPics()) {
            tbpicService.create(new TbpicDto.TbpicCreateDto(result.getId(), pic));
        }
        */
        return result;
    }
    public TbboardDto.TbboardAfterUpdateDto update(TbboardDto.TbboardUpdateDto params){
        Tbboard tbboard = tbboardRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbboard.setTitle(params.getTitle());
        }
        if(params.getContent() != null){
            tbboard.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbboard.setDeleted(params.getDeleted());
        }
        tbboardRepository.save(tbboard);
        return tbboard.toAfterUpdateDto();
    }

    public TbboardDto.TbboardSelectDto detail(String id){
        TbboardDto.TbboardSelectDto result = tbboardMapper.detail(id);
        result.setPics(tbpicService.list(new TbpicDto.TbpicListDto(id,null,"image",null)));
        result.setFiles(tbpicService.list(new TbpicDto.TbpicListDto(id,null,"file",null)));
        return result;
    }
    public List<TbboardDto.TbboardSelectDto> list(TbboardDto.TbboardListDto params){
        /*
        // 상세 정보 조회 하는 것을 디테일에만 맡길때
        List<TbboardDto.TbboardSelectDto> a_list = tbboardMapper.list(params);
        List<TbboardDto.TbboardSelectDto> result_list = new ArrayList<>();
        for(TbboardDto.TbboardSelectDto a : a_list){
            result_list.add(detail(a.getId()));
        }
        return result_list;
         */
        return tbboardMapper.list(params);
    }
    public List<TbboardDto.TbboardSelectDto> moreList(TbboardDto.TbboardMoreListDto params){
        params.afterBuild();
        return tbboardMapper.moreList(params);
    }
    public CommonAfterPagedListDto<TbboardDto.TbboardSelectDto> pagedList(TbboardDto.TbboardPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbboardMapper.pagedListCount(params)), tbboardMapper.pagedList(params));
    }

}
