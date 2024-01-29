package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbcmtDto;

import java.util.List;

public interface TbcmtMapper {
	TbcmtDto.TbcmtSelectDto detail(String id);
	List<TbcmtDto.TbcmtSelectDto> list(TbcmtDto.TbcmtListDto params);
	List<TbcmtDto.TbcmtSelectDto> moreList(TbcmtDto.TbcmtMoreListDto params);
	List<TbcmtDto.TbcmtSelectDto> pagedList(TbcmtDto.TbcmtPagedListDto params);
	int pagedListCount(TbcmtDto.TbcmtPagedListDto params);
}