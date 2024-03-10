package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbbannerDto;

import java.util.List;

public interface TbbannerMapper {
	TbbannerDto.TbbannerSelectDto random(TbbannerDto.TbbannerListDto params);
	/**/
	TbbannerDto.TbbannerSelectDto detail(String id);
	List<TbbannerDto.TbbannerSelectDto> list(TbbannerDto.TbbannerListDto params);
	List<TbbannerDto.TbbannerSelectDto> moreList(TbbannerDto.TbbannerMoreListDto params);
	List<TbbannerDto.TbbannerSelectDto> pagedList(TbbannerDto.TbbannerPagedListDto params);
	int pagedListCount(TbbannerDto.TbbannerPagedListDto params);
}