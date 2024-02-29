package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbgsquidDto;

import java.util.List;

public interface TbgsquidService {
    public TbgsquidDto.TbgsquidAfterUpdateDto next(TbgsquidDto.TbgsquidUpdateDto params);
    /**/
    public TbgsquidDto.TbgsquidAfterCreateDto create(TbgsquidDto.TbgsquidCreateDto params);
    public TbgsquidDto.TbgsquidAfterUpdateDto update(TbgsquidDto.TbgsquidUpdateDto params);
    public TbgsquidDto.TbgsquidAfterUpdateDto delete(TbgsquidDto.TbgsquidUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgsquidDto.TbgsquidSelectDto detail(CommonDetailDto params);
    public TbgsquidDto.TbgsquidSelectDto get(String id);
    public List<TbgsquidDto.TbgsquidSelectDto> list(TbgsquidDto.TbgsquidListDto params);
    public List<TbgsquidDto.TbgsquidSelectDto> moreList(TbgsquidDto.TbgsquidMoreListDto params);
    public CommonAfterPagedListDto<TbgsquidDto.TbgsquidSelectDto> pagedList(TbgsquidDto.TbgsquidPagedListDto params);
}
