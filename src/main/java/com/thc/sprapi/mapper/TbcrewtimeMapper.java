package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbcrewtimeDto;

import java.util.List;

public interface TbcrewtimeMapper {
	TbcrewtimeDto.TbcrewtimeSelectDto random(TbcrewtimeDto.TbcrewtimeListDto params);
	/**/
	TbcrewtimeDto.TbcrewtimeSelectDto detail(String id);
	List<TbcrewtimeDto.TbcrewtimeSelectDto> list(TbcrewtimeDto.TbcrewtimeListDto params);
	List<TbcrewtimeDto.TbcrewtimeSelectDto> moreList(TbcrewtimeDto.TbcrewtimeMoreListDto params);
	List<TbcrewtimeDto.TbcrewtimeSelectDto> pagedList(TbcrewtimeDto.TbcrewtimePagedListDto params);
	int pagedListCount(TbcrewtimeDto.TbcrewtimePagedListDto params);
}