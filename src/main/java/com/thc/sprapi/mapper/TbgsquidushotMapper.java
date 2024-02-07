package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgsquidushotDto;

import java.util.List;

public interface TbgsquidushotMapper {
	List<TbgsquidushotDto.TbgsquidushotSelectDto> next(TbgsquidushotDto.TbgsquidushotListDto params);
	/**/
	TbgsquidushotDto.TbgsquidushotSelectDto detail(String id);
	List<TbgsquidushotDto.TbgsquidushotSelectDto> list(TbgsquidushotDto.TbgsquidushotListDto params);
	List<TbgsquidushotDto.TbgsquidushotSelectDto> moreList(TbgsquidushotDto.TbgsquidushotMoreListDto params);
	List<TbgsquidushotDto.TbgsquidushotSelectDto> pagedList(TbgsquidushotDto.TbgsquidushotPagedListDto params);
	int pagedListCount(TbgsquidushotDto.TbgsquidushotPagedListDto params);
}