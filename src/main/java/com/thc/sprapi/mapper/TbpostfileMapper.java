package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileMapper {
	TbpostfileDto.TbpostfileSelectDto detail(String id);
	List<TbpostfileDto.TbpostfileSelectDto> list(TbpostfileDto.TbpostfileListDto params);
	List<TbpostfileDto.TbpostfileSelectDto> moreList(TbpostfileDto.TbpostfileMoreListDto params);
	List<TbpostfileDto.TbpostfileSelectDto> pagedList(TbpostfileDto.TbpostfilePagedListDto params);
	int pagedListCount(TbpostfileDto.TbpostfilePagedListDto params);
}