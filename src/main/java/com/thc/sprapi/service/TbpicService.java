package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbpicDto;

import java.util.List;

public interface TbpicService {
    public TbpicDto.TbpicAfterCreateDto create(TbpicDto.TbpicCreateDto params);
    public TbpicDto.TbpicAfterUpdateDto update(TbpicDto.TbpicUpdateDto params);
    public TbpicDto.TbpicAfterUpdateDto delete(TbpicDto.TbpicUpdateDto params);
    public TbpicDto.TbpicSelectDto detail(String id);
    public List<TbpicDto.TbpicSelectDto> list(TbpicDto.TbpicListDto params);
    public List<TbpicDto.TbpicSelectDto> moreList(TbpicDto.TbpicMoreListDto params);
    public CommonAfterPagedListDto<TbpicDto.TbpicSelectDto> pagedList(TbpicDto.TbpicPagedListDto params);
}
