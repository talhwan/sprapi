package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgsquidushotDto;

import java.util.List;

public interface TbgsquidushotService {
    public List<TbgsquidushotDto.TbgsquidushotSelectDto> next(TbgsquidushotDto.TbgsquidushotListDto params);
    public TbgsquidushotDto.TbgsquidushotAfterCreateDto join(TbgsquidushotDto.TbgsquidushotCreateDto params);
    /**/
    public TbgsquidushotDto.TbgsquidushotAfterCreateDto create(TbgsquidushotDto.TbgsquidushotCreateDto params);
    public TbgsquidushotDto.TbgsquidushotAfterUpdateDto update(TbgsquidushotDto.TbgsquidushotUpdateDto params);
    public TbgsquidushotDto.TbgsquidushotAfterUpdateDto delete(TbgsquidushotDto.TbgsquidushotUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgsquidushotDto.TbgsquidushotSelectDto detail(String id);
    public List<TbgsquidushotDto.TbgsquidushotSelectDto> list(TbgsquidushotDto.TbgsquidushotListDto params);
    public List<TbgsquidushotDto.TbgsquidushotSelectDto> moreList(TbgsquidushotDto.TbgsquidushotMoreListDto params);
    public CommonAfterPagedListDto<TbgsquidushotDto.TbgsquidushotSelectDto> pagedList(TbgsquidushotDto.TbgsquidushotPagedListDto params);
}
