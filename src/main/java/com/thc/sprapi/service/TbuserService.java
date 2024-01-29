package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbuserDto;

import java.util.List;

public interface TbuserService {

    public TbuserDto.TbuserAfterCreateDto signup(TbuserDto.TbuserCreateDto params);
    /**/
    public TbuserDto.TbuserAfterCreateDto create(TbuserDto.TbuserCreateDto params);
    public TbuserDto.TbuserAfterUpdateDto update(TbuserDto.TbuserUpdateDto params);
    public TbuserDto.TbuserSelectDto detail(String id);
    public List<TbuserDto.TbuserSelectDto> list(TbuserDto.TbuserListDto params);
    public List<TbuserDto.TbuserSelectDto> moreList(TbuserDto.TbuserMoreListDto params);
    public CommonAfterPagedListDto<TbuserDto.TbuserSelectDto> pagedList(TbuserDto.TbuserPagedListDto params);
}
