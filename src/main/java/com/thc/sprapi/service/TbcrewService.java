package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewDto;

import java.util.List;

public interface TbcrewService {
    /**/
    public TbcrewDto.TbcrewAfterCreateDto create(TbcrewDto.TbcrewCreateDto params);
    public TbcrewDto.TbcrewAfterUpdateDto update(TbcrewDto.TbcrewUpdateDto params);
    public TbcrewDto.TbcrewAfterUpdateDto delete(TbcrewDto.TbcrewUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbcrewDto.TbcrewSelectDto detail(CommonDetailDto params);
    public TbcrewDto.TbcrewSelectDto get(String id);
    public List<TbcrewDto.TbcrewSelectDto> list(TbcrewDto.TbcrewListDto params);
    public List<TbcrewDto.TbcrewSelectDto> moreList(TbcrewDto.TbcrewMoreListDto params);
    public CommonAfterPagedListDto<TbcrewDto.TbcrewSelectDto> pagedList(TbcrewDto.TbcrewPagedListDto params);
}
