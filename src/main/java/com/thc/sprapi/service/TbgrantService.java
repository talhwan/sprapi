package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgrantDto;

import java.util.List;

public interface TbgrantService {
    public boolean access(TbgrantDto.TbgrantAccessDto params);
    public TbgrantDto.TbgrantAfterCreateDto create(TbgrantDto.TbgrantCreateDto params);
    public TbgrantDto.TbgrantAfterUpdateDto update(TbgrantDto.TbgrantUpdateDto params);
    public TbgrantDto.TbgrantAfterUpdateDto delete(TbgrantDto.TbgrantUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgrantDto.TbgrantSelectDto detail(String id);
    public List<TbgrantDto.TbgrantSelectDto> list(TbgrantDto.TbgrantListDto params);
    public List<TbgrantDto.TbgrantSelectDto> moreList(TbgrantDto.TbgrantMoreListDto params);
    public CommonAfterPagedListDto<TbgrantDto.TbgrantSelectDto> pagedList(TbgrantDto.TbgrantPagedListDto params);
}
