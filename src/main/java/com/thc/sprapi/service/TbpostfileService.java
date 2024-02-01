package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileService {
    public TbpostfileDto.TbpostfileAfterCreateDto create(TbpostfileDto.TbpostfileCreateDto params);
    public TbpostfileDto.TbpostfileAfterUpdateDto update(TbpostfileDto.TbpostfileUpdateDto params);
    public TbpostfileDto.TbpostfileAfterUpdateDto delete(TbpostfileDto.TbpostfileUpdateDto params);
    public TbpostfileDto.TbpostfileSelectDto detail(String id);
    public List<TbpostfileDto.TbpostfileSelectDto> list(TbpostfileDto.TbpostfileListDto params);
    public List<TbpostfileDto.TbpostfileSelectDto> moreList(TbpostfileDto.TbpostfileMoreListDto params);
    public CommonAfterPagedListDto<TbpostfileDto.TbpostfileSelectDto> pagedList(TbpostfileDto.TbpostfilePagedListDto params);
}
