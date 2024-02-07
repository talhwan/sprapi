package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgsquidDto;

import java.util.List;

public interface TbgsquidMapper {
	TbgsquidDto.TbgsquidSelectDto detail(String id);
	List<TbgsquidDto.TbgsquidSelectDto> list(TbgsquidDto.TbgsquidListDto params);
	List<TbgsquidDto.TbgsquidSelectDto> moreList(TbgsquidDto.TbgsquidMoreListDto params);
	List<TbgsquidDto.TbgsquidSelectDto> pagedList(TbgsquidDto.TbgsquidPagedListDto params);
	int pagedListCount(TbgsquidDto.TbgsquidPagedListDto params);
}