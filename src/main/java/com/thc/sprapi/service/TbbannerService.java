package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbbannerDto;

import java.util.List;

public interface TbbannerService {
    public TbbannerDto.TbbannerSelectDto recommend();
    public TbbannerDto.TbbannerAfterUpdateDto sequence(TbbannerDto.TbbannerSequenceDto params);
    /**/
    public TbbannerDto.TbbannerAfterCreateDto create(TbbannerDto.TbbannerCreateDto params);
    public TbbannerDto.TbbannerAfterUpdateDto update(TbbannerDto.TbbannerUpdateDto params);
    public TbbannerDto.TbbannerAfterUpdateDto delete(TbbannerDto.TbbannerUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbbannerDto.TbbannerSelectDto detail(CommonDetailDto params);
    public TbbannerDto.TbbannerSelectDto get(String id);
    public List<TbbannerDto.TbbannerSelectDto> list(TbbannerDto.TbbannerListDto params);
    public List<TbbannerDto.TbbannerSelectDto> moreList(TbbannerDto.TbbannerMoreListDto params);
    public CommonAfterPagedListDto<TbbannerDto.TbbannerSelectDto> pagedList(TbbannerDto.TbbannerPagedListDto params);
}
