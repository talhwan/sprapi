package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbcrewDto;

import java.util.List;

public interface TbcrewMapper {
	TbcrewDto.TbcrewSelectDto detail(String id);
	List<TbcrewDto.TbcrewSelectDto> list(TbcrewDto.TbcrewListDto params);
	List<TbcrewDto.TbcrewSelectDto> moreList(TbcrewDto.TbcrewMoreListDto params);
	List<TbcrewDto.TbcrewSelectDto> pagedList(TbcrewDto.TbcrewPagedListDto params);
	int pagedListCount(TbcrewDto.TbcrewPagedListDto params);
}