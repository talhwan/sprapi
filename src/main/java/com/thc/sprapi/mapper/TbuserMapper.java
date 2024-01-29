package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
	TbuserDto.TbuserSelectDto detail(String id);
	List<TbuserDto.TbuserSelectDto> list(TbuserDto.TbuserListDto params);
	List<TbuserDto.TbuserSelectDto> moreList(TbuserDto.TbuserMoreListDto params);
	List<TbuserDto.TbuserSelectDto> pagedList(TbuserDto.TbuserPagedListDto params);
	int pagedListCount(TbuserDto.TbuserPagedListDto params);
}