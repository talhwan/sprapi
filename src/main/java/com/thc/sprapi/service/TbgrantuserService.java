package com.thc.sprapi.service;

import com.thc.sprapi.dto.*;
import com.thc.sprapi.dto.TbgrantuserDto;

import java.util.List;

public interface TbgrantuserService {
    public TbgrantuserDto.TbgrantuserAfterCreateDto toggle(TbgrantuserDto.TbgrantuserToggleDto params);
    public List<TbgrantuserDto.TbgrantuserSelectDto> moreListByTbgrantId(TbgrantuserDto.TbgrantuserMoreListDto params);
    /**/
    public TbgrantuserDto.TbgrantuserAfterCreateDto create(TbgrantuserDto.TbgrantuserCreateDto params);
    public TbgrantuserDto.TbgrantuserAfterUpdateDto update(TbgrantuserDto.TbgrantuserUpdateDto params);
    public TbgrantuserDto.TbgrantuserAfterUpdateDto delete(TbgrantuserDto.TbgrantuserUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgrantuserDto.TbgrantuserSelectDto detail(CommonDetailDto params);
    public TbgrantuserDto.TbgrantuserSelectDto get(String id);
    public List<TbgrantuserDto.TbgrantuserSelectDto> list(TbgrantuserDto.TbgrantuserListDto params);
    public List<TbgrantuserDto.TbgrantuserSelectDto> moreList(TbgrantuserDto.TbgrantuserMoreListDto params);
    public CommonAfterPagedListDto<TbgrantuserDto.TbgrantuserSelectDto> pagedList(TbgrantuserDto.TbgrantuserPagedListDto params);
}
