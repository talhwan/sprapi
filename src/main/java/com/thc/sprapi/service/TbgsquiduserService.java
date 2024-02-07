package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.TbgsquiduserDto;

import java.util.List;

public interface TbgsquiduserService {
    public List<TbgsquiduserDto.TbgsquiduserSelectDto> listByUnderProcess(TbgsquiduserDto.TbgsquiduserListDto params);
    /**/
    public TbgsquiduserDto.TbgsquiduserAfterCreateDto join(TbgsquiduserDto.TbgsquiduserCreateDto params);
    public TbgsquiduserDto.TbgsquiduserAfterCreateDto create(TbgsquiduserDto.TbgsquiduserCreateDto params);
    public TbgsquiduserDto.TbgsquiduserAfterUpdateDto update(TbgsquiduserDto.TbgsquiduserUpdateDto params);
    public TbgsquiduserDto.TbgsquiduserAfterUpdateDto delete(TbgsquiduserDto.TbgsquiduserUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgsquiduserDto.TbgsquiduserSelectDto detail(String id);
    public List<TbgsquiduserDto.TbgsquiduserSelectDto> list(TbgsquiduserDto.TbgsquiduserListDto params);
    public List<TbgsquiduserDto.TbgsquiduserSelectDto> moreList(TbgsquiduserDto.TbgsquiduserMoreListDto params);
    public CommonAfterPagedListDto<TbgsquiduserDto.TbgsquiduserSelectDto> pagedList(TbgsquiduserDto.TbgsquiduserPagedListDto params);
}
