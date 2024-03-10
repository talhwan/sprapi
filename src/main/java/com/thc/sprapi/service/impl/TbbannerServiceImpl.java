package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbbanner;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbbannerDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbbannerMapper;
import com.thc.sprapi.repository.TbbannerRepository;
import com.thc.sprapi.service.TbbannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbbannerServiceImpl implements TbbannerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbbannerRepository tbbannerRepository;
    private final TbbannerMapper tbbannerMapper;
    public TbbannerServiceImpl(
            TbbannerRepository tbbannerRepository
            ,TbbannerMapper tbbannerMapper
    ) {
        this.tbbannerRepository = tbbannerRepository;
        this.tbbannerMapper = tbbannerMapper;
    }

    public TbbannerDto.TbbannerSelectDto recommend(){
        TbbannerDto.TbbannerListDto params = TbbannerDto.TbbannerListDto.builder().deleted("N").build();
        return get(tbbannerMapper.random(params).getId());
    }

    public TbbannerDto.TbbannerAfterUpdateDto sequence(TbbannerDto.TbbannerSequenceDto params){

        Tbbanner tbbanner = tbbannerRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        int nowSequence = tbbanner.getSequence();

        int targetSequence = nowSequence;
        if("up".equals(params.getWay())){
            targetSequence++;
        } else {
            targetSequence--;
        }
        if(targetSequence == 0 || targetSequence > tbbannerMapper.pagedListCount(new TbbannerDto.TbbannerPagedListDto())){
            return null;
            //throw new CommonException("");
        } else {
            //잠시 순번에서 제외
            update(TbbannerDto.TbbannerUpdateDto.builder().id(tbbanner.getId()).sequence(-1).build());
            //바꾸고자 하는 순번의 정보에 이전 순번 저장
            Tbbanner targetTbbanner = tbbannerRepository.findBySequence(targetSequence);
            update(TbbannerDto.TbbannerUpdateDto.builder().id(targetTbbanner.getId()).sequence(nowSequence).build());
            //다시 수정하고 리턴
            return update(TbbannerDto.TbbannerUpdateDto.builder().id(tbbanner.getId()).sequence(targetSequence).build());
        }
    }

    /**/

    public TbbannerDto.TbbannerAfterCreateDto create(TbbannerDto.TbbannerCreateDto params){
        params.setSequence(tbbannerMapper.pagedListCount(new TbbannerDto.TbbannerPagedListDto()) + 1);
        return tbbannerRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbbannerDto.TbbannerAfterUpdateDto update(TbbannerDto.TbbannerUpdateDto params){
        Tbbanner tbbanner = tbbannerRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbbanner.setTitle(params.getTitle());
        }
        if(params.getSequence() != null){
            tbbanner.setSequence(params.getSequence());
        }
        if(params.getCate() != null){
            tbbanner.setCate(params.getCate());
        }
        if(params.getMpic() != null){
            tbbanner.setMpic(params.getMpic());
        }
        if(params.getUrl() != null){
            tbbanner.setUrl(params.getUrl());
        }
        if(params.getContent() != null){
            tbbanner.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbbanner.setDeleted(params.getDeleted());
        }
        tbbannerRepository.save(tbbanner);
        return tbbanner.toAfterUpdateDto();
    }
    public TbbannerDto.TbbannerAfterUpdateDto delete(TbbannerDto.TbbannerUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbbannerDto.TbbannerUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbbannerDto.TbbannerSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbbannerDto.TbbannerSelectDto get(String id){
        return tbbannerMapper.detail(id);
    }
    public List<TbbannerDto.TbbannerSelectDto> list(TbbannerDto.TbbannerListDto params){
        return addListDetails(tbbannerMapper.list(params));
    }
    public List<TbbannerDto.TbbannerSelectDto> moreList(TbbannerDto.TbbannerMoreListDto params){
        params.afterBuild();
        return addListDetails(tbbannerMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbbannerDto.TbbannerSelectDto> pagedList(TbbannerDto.TbbannerPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbbannerMapper.pagedListCount(params)), addListDetails(tbbannerMapper.pagedList(params)));
    }

    public List<TbbannerDto.TbbannerSelectDto> addListDetails(List<TbbannerDto.TbbannerSelectDto> a_list){
        List<TbbannerDto.TbbannerSelectDto> result_list = new ArrayList<>();
        for(TbbannerDto.TbbannerSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
