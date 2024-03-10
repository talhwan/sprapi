package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbpopupDto;

import java.util.List;

public interface TbpopupMapper {
	TbpopupDto.TbpopupSelectDto detail(String id);
	List<TbpopupDto.TbpopupSelectDto> list(TbpopupDto.TbpopupListDto params);
	List<TbpopupDto.TbpopupSelectDto> moreList(TbpopupDto.TbpopupMoreListDto params);
	List<TbpopupDto.TbpopupSelectDto> pagedList(TbpopupDto.TbpopupPagedListDto params);
	int pagedListCount(TbpopupDto.TbpopupPagedListDto params);
}