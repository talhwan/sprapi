package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewuserDto;

import java.util.List;

public interface TbcrewuserService {
    public TbcrewuserDto.TbcrewuserAfterCreateDto join(TbcrewuserDto.TbcrewuserCreateDto params);
    /**/
    public TbcrewuserDto.TbcrewuserAfterCreateDto create(TbcrewuserDto.TbcrewuserCreateDto params);
    public TbcrewuserDto.TbcrewuserAfterUpdateDto update(TbcrewuserDto.TbcrewuserUpdateDto params);
    public TbcrewuserDto.TbcrewuserAfterUpdateDto delete(TbcrewuserDto.TbcrewuserUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbcrewuserDto.TbcrewuserSelectDto detail(CommonDetailDto params);
    public TbcrewuserDto.TbcrewuserSelectDto get(String id);
    public List<TbcrewuserDto.TbcrewuserSelectDto> list(TbcrewuserDto.TbcrewuserListDto params);
    public List<TbcrewuserDto.TbcrewuserSelectDto> moreList(TbcrewuserDto.TbcrewuserMoreListDto params);
    public CommonAfterPagedListDto<TbcrewuserDto.TbcrewuserSelectDto> pagedList(TbcrewuserDto.TbcrewuserPagedListDto params);
}
