package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbpopupDto;

import java.util.List;

public interface TbpopupService {
    public TbpopupDto.TbpopupAfterUpdateDto sequence(TbpopupDto.TbpopupSequenceDto params);
    /**/
    public TbpopupDto.TbpopupAfterCreateDto create(TbpopupDto.TbpopupCreateDto params);
    public TbpopupDto.TbpopupAfterUpdateDto update(TbpopupDto.TbpopupUpdateDto params);
    public TbpopupDto.TbpopupAfterUpdateDto delete(TbpopupDto.TbpopupUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbpopupDto.TbpopupSelectDto detail(CommonDetailDto params);
    public TbpopupDto.TbpopupSelectDto get(String id);
    public List<TbpopupDto.TbpopupSelectDto> list(TbpopupDto.TbpopupListDto params);
    public List<TbpopupDto.TbpopupSelectDto> moreList(TbpopupDto.TbpopupMoreListDto params);
    public CommonAfterPagedListDto<TbpopupDto.TbpopupSelectDto> pagedList(TbpopupDto.TbpopupPagedListDto params);
}
