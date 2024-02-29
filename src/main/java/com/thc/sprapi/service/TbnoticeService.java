package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbnoticeDto;

import java.util.List;

public interface TbnoticeService {
    public TbnoticeDto.TbnoticeAfterCreateDto create(TbnoticeDto.TbnoticeCreateDto params);
    public TbnoticeDto.TbnoticeAfterUpdateDto update(TbnoticeDto.TbnoticeUpdateDto params);
    public TbnoticeDto.TbnoticeAfterUpdateDto delete(TbnoticeDto.TbnoticeUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbnoticeDto.TbnoticeSelectDto detail(CommonDetailDto params);
    public TbnoticeDto.TbnoticeSelectDto get(String id);
    public List<TbnoticeDto.TbnoticeSelectDto> list(TbnoticeDto.TbnoticeListDto params);
    public List<TbnoticeDto.TbnoticeSelectDto> moreList(TbnoticeDto.TbnoticeMoreListDto params);
    public CommonAfterPagedListDto<TbnoticeDto.TbnoticeSelectDto> pagedList(TbnoticeDto.TbnoticePagedListDto params);
}
