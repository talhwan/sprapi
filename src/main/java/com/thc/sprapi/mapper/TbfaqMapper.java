package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbfaqDto;

import java.util.List;

public interface TbfaqMapper {
	TbfaqDto.TbfaqSelectDto detail(String id);
	List<TbfaqDto.TbfaqSelectDto> list(TbfaqDto.TbfaqListDto params);
	List<TbfaqDto.TbfaqSelectDto> moreList(TbfaqDto.TbfaqMoreListDto params);
	List<TbfaqDto.TbfaqSelectDto> pagedList(TbfaqDto.TbfaqPagedListDto params);
	int pagedListCount(TbfaqDto.TbfaqPagedListDto params);
}