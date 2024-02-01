package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtMapper {
	TbpostcmtDto.TbpostcmtSelectDto detail(String id);
	List<TbpostcmtDto.TbpostcmtSelectDto> list(TbpostcmtDto.TbpostcmtListDto params);
	List<TbpostcmtDto.TbpostcmtSelectDto> moreList(TbpostcmtDto.TbpostcmtMoreListDto params);
	List<TbpostcmtDto.TbpostcmtSelectDto> pagedList(TbpostcmtDto.TbpostcmtPagedListDto params);
	int pagedListCount(TbpostcmtDto.TbpostcmtPagedListDto params);
}