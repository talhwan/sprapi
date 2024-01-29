package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbcmtDto;

import java.util.List;

public interface TbcmtService {
    public TbcmtDto.TbcmtAfterCreateDto create(TbcmtDto.TbcmtCreateDto params);
    public TbcmtDto.TbcmtAfterUpdateDto update(TbcmtDto.TbcmtUpdateDto params);
    public TbcmtDto.TbcmtSelectDto detail(String id);
    public List<TbcmtDto.TbcmtSelectDto> list(TbcmtDto.TbcmtListDto params);
    public List<TbcmtDto.TbcmtSelectDto> moreList(TbcmtDto.TbcmtMoreListDto params);
    public CommonAfterPagedListDto<TbcmtDto.TbcmtSelectDto> pagedList(TbcmtDto.TbcmtPagedListDto params);
}
