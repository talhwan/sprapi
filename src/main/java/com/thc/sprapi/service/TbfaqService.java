package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbfaqDto;

import java.util.List;

public interface TbfaqService {
    public TbfaqDto.TbfaqAfterUpdateDto sequence(TbfaqDto.TbfaqSequenceDto params);
    /**/
    public TbfaqDto.TbfaqAfterCreateDto create(TbfaqDto.TbfaqCreateDto params);
    public TbfaqDto.TbfaqAfterUpdateDto update(TbfaqDto.TbfaqUpdateDto params);
    public TbfaqDto.TbfaqAfterUpdateDto delete(TbfaqDto.TbfaqUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbfaqDto.TbfaqSelectDto detail(CommonDetailDto params);
    public TbfaqDto.TbfaqSelectDto get(String id);
    public List<TbfaqDto.TbfaqSelectDto> list(TbfaqDto.TbfaqListDto params);
    public List<TbfaqDto.TbfaqSelectDto> moreList(TbfaqDto.TbfaqMoreListDto params);
    public CommonAfterPagedListDto<TbfaqDto.TbfaqSelectDto> pagedList(TbfaqDto.TbfaqPagedListDto params);
}
