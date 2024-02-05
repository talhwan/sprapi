package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgrantpartDto;

import java.util.List;

public interface TbgrantpartMapper {
	TbgrantpartDto.TbgrantpartSelectDto detail(String id);
	List<TbgrantpartDto.TbgrantpartSelectDto> list(TbgrantpartDto.TbgrantpartListDto params);
	List<TbgrantpartDto.TbgrantpartSelectDto> moreList(TbgrantpartDto.TbgrantpartMoreListDto params);
	List<TbgrantpartDto.TbgrantpartSelectDto> pagedList(TbgrantpartDto.TbgrantpartPagedListDto params);
	int pagedListCount(TbgrantpartDto.TbgrantpartPagedListDto params);
}