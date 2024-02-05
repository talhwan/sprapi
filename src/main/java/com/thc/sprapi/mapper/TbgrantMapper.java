package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgrantDto;

import java.util.List;

public interface TbgrantMapper {
	TbgrantDto.TbgrantSelectDto detail(String id);
	List<TbgrantDto.TbgrantSelectDto> list(TbgrantDto.TbgrantListDto params);
	List<TbgrantDto.TbgrantSelectDto> moreList(TbgrantDto.TbgrantMoreListDto params);
	List<TbgrantDto.TbgrantSelectDto> pagedList(TbgrantDto.TbgrantPagedListDto params);
	int pagedListCount(TbgrantDto.TbgrantPagedListDto params);
}