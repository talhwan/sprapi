package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbpicDto;

import java.util.List;

public interface TbpicMapper {
	TbpicDto.TbpicSelectDto detail(String id);
	List<TbpicDto.TbpicSelectDto> list(TbpicDto.TbpicListDto params);
	List<TbpicDto.TbpicSelectDto> moreList(TbpicDto.TbpicMoreListDto params);
	List<TbpicDto.TbpicSelectDto> pagedList(TbpicDto.TbpicPagedListDto params);
	int pagedListCount(TbpicDto.TbpicPagedListDto params);
}