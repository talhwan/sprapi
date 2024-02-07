package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbgsquiduserDto;

import java.util.List;

public interface TbgsquiduserMapper {
	List<TbgsquiduserDto.TbgsquiduserSelectDto> listByUnderProcess(TbgsquiduserDto.TbgsquiduserListDto params);
	/**/
	TbgsquiduserDto.TbgsquiduserSelectDto detail(String id);
	List<TbgsquiduserDto.TbgsquiduserSelectDto> list(TbgsquiduserDto.TbgsquiduserListDto params);
	List<TbgsquiduserDto.TbgsquiduserSelectDto> moreList(TbgsquiduserDto.TbgsquiduserMoreListDto params);
	List<TbgsquiduserDto.TbgsquiduserSelectDto> pagedList(TbgsquiduserDto.TbgsquiduserPagedListDto params);
	int pagedListCount(TbgsquiduserDto.TbgsquiduserPagedListDto params);
}