package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbpostDto;

import java.util.List;

public interface TbpostMapper {
	TbpostDto.TbpostSelectDto detail(String id);
	List<TbpostDto.TbpostSelectDto> list(TbpostDto.TbpostListDto params);
	List<TbpostDto.TbpostSelectDto> moreList(TbpostDto.TbpostMoreListDto params);
	List<TbpostDto.TbpostSelectDto> pagedList(TbpostDto.TbpostPagedListDto params);
	int pagedListCount(TbpostDto.TbpostPagedListDto params);
}