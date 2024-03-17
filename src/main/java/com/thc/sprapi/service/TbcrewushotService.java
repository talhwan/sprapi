package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewushotDto;

import java.util.List;

public interface TbcrewushotService {
    /**/
    public TbcrewushotDto.TbcrewushotAfterCreateDto create(TbcrewushotDto.TbcrewushotCreateDto params);
    public TbcrewushotDto.TbcrewushotAfterUpdateDto update(TbcrewushotDto.TbcrewushotUpdateDto params);
    public TbcrewushotDto.TbcrewushotAfterUpdateDto delete(TbcrewushotDto.TbcrewushotUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbcrewushotDto.TbcrewushotSelectDto detail(CommonDetailDto params);
    public TbcrewushotDto.TbcrewushotSelectDto get(String id);
    public List<TbcrewushotDto.TbcrewushotSelectDto> list(TbcrewushotDto.TbcrewushotListDto params);
    public List<TbcrewushotDto.TbcrewushotSelectDto> moreList(TbcrewushotDto.TbcrewushotMoreListDto params);
    public CommonAfterPagedListDto<TbcrewushotDto.TbcrewushotSelectDto> pagedList(TbcrewushotDto.TbcrewushotPagedListDto params);
}
