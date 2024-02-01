package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtService {
    public TbpostcmtDto.TbpostcmtAfterCreateDto create(TbpostcmtDto.TbpostcmtCreateDto params);
    public TbpostcmtDto.TbpostcmtAfterUpdateDto update(TbpostcmtDto.TbpostcmtUpdateDto params);
    public TbpostcmtDto.TbpostcmtAfterUpdateDto delete(TbpostcmtDto.TbpostcmtUpdateDto params);
    public TbpostcmtDto.TbpostcmtSelectDto detail(String id);
    public List<TbpostcmtDto.TbpostcmtSelectDto> list(TbpostcmtDto.TbpostcmtListDto params);
    public List<TbpostcmtDto.TbpostcmtSelectDto> moreList(TbpostcmtDto.TbpostcmtMoreListDto params);
    public CommonAfterPagedListDto<TbpostcmtDto.TbpostcmtSelectDto> pagedList(TbpostcmtDto.TbpostcmtPagedListDto params);
}
