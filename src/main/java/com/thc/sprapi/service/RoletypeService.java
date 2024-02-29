package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.RoletypeDto;
import com.thc.sprapi.dto.RoletypeDto;

import java.util.List;

public interface RoletypeService {
    public RoletypeDto.RoletypeSelectDto create(RoletypeDto.RoletypeCreateDto params);
    public RoletypeDto.RoletypeSelectDto delete(String id);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);
    public RoletypeDto.RoletypeSelectDto detail(String id);
    public List<RoletypeDto.RoletypeSelectDto> list();
}
