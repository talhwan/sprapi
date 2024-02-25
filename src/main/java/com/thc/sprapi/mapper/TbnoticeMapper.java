package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbnoticeDto;

import java.util.List;

public interface TbnoticeMapper {
	TbnoticeDto.TbnoticeSelectDto detail(String id);
	List<TbnoticeDto.TbnoticeSelectDto> list(TbnoticeDto.TbnoticeListDto params);
	List<TbnoticeDto.TbnoticeSelectDto> moreList(TbnoticeDto.TbnoticeMoreListDto params);
	List<TbnoticeDto.TbnoticeSelectDto> pagedList(TbnoticeDto.TbnoticePagedListDto params);
	int pagedListCount(TbnoticeDto.TbnoticePagedListDto params);
}