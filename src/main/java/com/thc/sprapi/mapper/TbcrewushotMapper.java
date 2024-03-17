package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbcrewushotDto;

import java.util.List;

public interface TbcrewushotMapper {
	List<TbcrewushotDto.TbcrewushotSelectDto> next(TbcrewushotDto.TbcrewushotListDto params);
	/**/
	TbcrewushotDto.TbcrewushotSelectDto detail(String id);
	List<TbcrewushotDto.TbcrewushotSelectDto> list(TbcrewushotDto.TbcrewushotListDto params);
	List<TbcrewushotDto.TbcrewushotSelectDto> moreList(TbcrewushotDto.TbcrewushotMoreListDto params);
	List<TbcrewushotDto.TbcrewushotSelectDto> pagedList(TbcrewushotDto.TbcrewushotPagedListDto params);
	int pagedListCount(TbcrewushotDto.TbcrewushotPagedListDto params);
}