package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbboardDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TbboardService {
    public TbboardDto.TbboardAfterCreateDto create(TbboardDto.TbboardCreateDto params);
    public TbboardDto.TbboardAfterUpdateDto update(TbboardDto.TbboardUpdateDto params);
    public TbboardDto.TbboardSelectDto detail(String id);
    public List<TbboardDto.TbboardSelectDto> list(TbboardDto.TbboardListDto params);
    public List<TbboardDto.TbboardSelectDto> moreList(TbboardDto.TbboardMoreListDto params);
    public CommonAfterPagedListDto<TbboardDto.TbboardSelectDto> pagedList(TbboardDto.TbboardPagedListDto params);
}
