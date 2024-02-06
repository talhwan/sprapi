package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgrantuserDto;

import java.util.List;

public interface TbgrantuserMapper {
	List<TbgrantuserDto.TbgrantuserSelectDto> moreListByTbgrantId(TbgrantuserDto.TbgrantuserMoreListDto params);
	/**/
	TbgrantuserDto.TbgrantuserSelectDto detail(String id);
	List<TbgrantuserDto.TbgrantuserSelectDto> list(TbgrantuserDto.TbgrantuserListDto params);
	List<TbgrantuserDto.TbgrantuserSelectDto> moreList(TbgrantuserDto.TbgrantuserMoreListDto params);
	List<TbgrantuserDto.TbgrantuserSelectDto> pagedList(TbgrantuserDto.TbgrantuserPagedListDto params);
	int pagedListCount(TbgrantuserDto.TbgrantuserPagedListDto params);
}