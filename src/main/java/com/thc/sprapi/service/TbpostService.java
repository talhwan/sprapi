package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbpostDto;

import java.util.List;

public interface TbpostService {
    public TbpostDto.TbpostAfterCreateDto create(TbpostDto.TbpostCreateDto params);
    public TbpostDto.TbpostAfterUpdateDto update(TbpostDto.TbpostUpdateDto params);
    public TbpostDto.TbpostAfterUpdateDto delete(TbpostDto.TbpostUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public TbpostDto.TbpostSelectDto detail(CommonDetailDto params);
    public TbpostDto.TbpostSelectDto get(String id);
    public List<TbpostDto.TbpostSelectDto> list(TbpostDto.TbpostListDto params);
    public List<TbpostDto.TbpostSelectDto> moreList(TbpostDto.TbpostMoreListDto params);
    public CommonAfterPagedListDto<TbpostDto.TbpostSelectDto> pagedList(TbpostDto.TbpostPagedListDto params);
}
