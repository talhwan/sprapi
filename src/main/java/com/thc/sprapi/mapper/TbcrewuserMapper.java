package com.thc.sprapi.mapper;

import com.thc.sprapi.dto.TbcrewuserDto;

import java.util.List;

public interface TbcrewuserMapper {
	/**/
	TbcrewuserDto.TbcrewuserSelectDto detail(String id);
	List<TbcrewuserDto.TbcrewuserSelectDto> list(TbcrewuserDto.TbcrewuserListDto params);
	List<TbcrewuserDto.TbcrewuserSelectDto> moreList(TbcrewuserDto.TbcrewuserMoreListDto params);
	List<TbcrewuserDto.TbcrewuserSelectDto> pagedList(TbcrewuserDto.TbcrewuserPagedListDto params);
	int pagedListCount(TbcrewuserDto.TbcrewuserPagedListDto params);
}