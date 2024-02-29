package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbgrantpartDto;

import java.util.List;

public interface TbgrantpartService {
    public TbgrantpartDto.TbgrantpartAfterCreateDto toggle(TbgrantpartDto.TbgrantpartToggleDto params);
    /**/
    public TbgrantpartDto.TbgrantpartAfterCreateDto create(TbgrantpartDto.TbgrantpartCreateDto params);
    public TbgrantpartDto.TbgrantpartAfterUpdateDto update(TbgrantpartDto.TbgrantpartUpdateDto params);
    public TbgrantpartDto.TbgrantpartAfterUpdateDto delete(TbgrantpartDto.TbgrantpartUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbgrantpartDto.TbgrantpartSelectDto detail(CommonDetailDto params);
    public TbgrantpartDto.TbgrantpartSelectDto get(String id);
    public List<TbgrantpartDto.TbgrantpartSelectDto> list(TbgrantpartDto.TbgrantpartListDto params);
    public List<TbgrantpartDto.TbgrantpartSelectDto> moreList(TbgrantpartDto.TbgrantpartMoreListDto params);
    public CommonAfterPagedListDto<TbgrantpartDto.TbgrantpartSelectDto> pagedList(TbgrantpartDto.TbgrantpartPagedListDto params);
}
