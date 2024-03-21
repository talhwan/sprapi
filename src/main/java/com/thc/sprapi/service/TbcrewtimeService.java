package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewtimeDto;

import java.util.List;

public interface TbcrewtimeService {
    public TbcrewtimeDto.TbcrewtimeSelectDto recommend();
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto sequence(TbcrewtimeDto.TbcrewtimeSequenceDto params);
    /**/
    public TbcrewtimeDto.TbcrewtimeAfterCreateDto create(TbcrewtimeDto.TbcrewtimeCreateDto params);
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto update(TbcrewtimeDto.TbcrewtimeUpdateDto params);
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto delete(TbcrewtimeDto.TbcrewtimeUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbcrewtimeDto.TbcrewtimeSelectDto detail(CommonDetailDto params);
    public TbcrewtimeDto.TbcrewtimeSelectDto get(String id);
    public List<TbcrewtimeDto.TbcrewtimeSelectDto> list(TbcrewtimeDto.TbcrewtimeListDto params);
    public List<TbcrewtimeDto.TbcrewtimeSelectDto> moreList(TbcrewtimeDto.TbcrewtimeMoreListDto params);
    public CommonAfterPagedListDto<TbcrewtimeDto.TbcrewtimeSelectDto> pagedList(TbcrewtimeDto.TbcrewtimePagedListDto params);
}
