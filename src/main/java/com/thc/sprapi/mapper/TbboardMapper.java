package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbboardDto;

import java.util.List;
import java.util.Map;

public interface TbboardMapper {
	TbboardDto.TbboardSelectDto detail(String id);
	List<TbboardDto.TbboardSelectDto> list(TbboardDto.TbboardListDto params);
	List<TbboardDto.TbboardSelectDto> moreList(TbboardDto.TbboardMoreListDto params);
	List<TbboardDto.TbboardSelectDto> pagedList(TbboardDto.TbboardPagedListDto params);
	int pagedListCount(TbboardDto.TbboardPagedListDto params);
}