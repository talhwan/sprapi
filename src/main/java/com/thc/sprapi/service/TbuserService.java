package com.thc.sprapi.service;

import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.security.JwtTokenDto;

import java.util.List;
import java.util.Map;

public interface TbuserService {

    public TbuserDto.TbuserOkcertTokenDto okcert(String rootPath) throws Exception;
    public JwtTokenDto naver(String param);
    public JwtTokenDto sns(TbuserDto.TbuserCreateDto params);
    public TbuserDto.TbuserAfterCreateDto signup(TbuserDto.TbuserCreateDto params);
    public TbuserDto.TbuserAfterUpdateDto logout(TbuserDto.TbuserUpdateDto params);
    /**/
    public TbuserDto.TbuserAfterCreateDto create(TbuserDto.TbuserCreateDto params);
    public TbuserDto.TbuserAfterUpdateDto update(TbuserDto.TbuserUpdateDto params);

    public TbuserDto.TbuserAfterUpdateDto delete(TbuserDto.TbuserUpdateDto params);
    public CommonDeleteListDto deleteList(CommonDeleteListDto params);

    public TbuserDto.TbuserSelectDto detail(CommonDetailDto params);
    public TbuserDto.TbuserSelectDto get(String id);
    public List<TbuserDto.TbuserSelectDto> list(TbuserDto.TbuserListDto params);
    public List<TbuserDto.TbuserSelectDto> moreList(TbuserDto.TbuserMoreListDto params);
    public CommonAfterPagedListDto<TbuserDto.TbuserSelectDto> pagedList(TbuserDto.TbuserPagedListDto params);
}
