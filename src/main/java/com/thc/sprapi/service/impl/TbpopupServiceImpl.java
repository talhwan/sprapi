package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbpopup;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbpopupDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbpopupMapper;
import com.thc.sprapi.repository.TbpopupRepository;
import com.thc.sprapi.service.TbpopupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpopupServiceImpl implements TbpopupService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpopupRepository tbpopupRepository;
    private final TbpopupMapper tbpopupMapper;
    public TbpopupServiceImpl(
            TbpopupRepository tbpopupRepository
            ,TbpopupMapper tbpopupMapper
    ) {
        this.tbpopupRepository = tbpopupRepository;
        this.tbpopupMapper = tbpopupMapper;
    }

    public TbpopupDto.TbpopupAfterUpdateDto sequence(TbpopupDto.TbpopupSequenceDto params){

        Tbpopup tbpopup = tbpopupRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        int nowSequence = tbpopup.getSequence();

        int targetSequence = nowSequence;
        if("up".equals(params.getWay())){
            targetSequence++;
        } else {
            targetSequence--;
        }
        if(targetSequence == 0 || targetSequence > tbpopupMapper.pagedListCount(new TbpopupDto.TbpopupPagedListDto())){
            return null;
            //throw new CommonException("");
        } else {
            //잠시 순번에서 제외
            update(TbpopupDto.TbpopupUpdateDto.builder().id(tbpopup.getId()).sequence(-1).build());
            //바꾸고자 하는 순번의 정보에 이전 순번 저장
            Tbpopup targetTbpopup = tbpopupRepository.findBySequence(targetSequence);
            update(TbpopupDto.TbpopupUpdateDto.builder().id(targetTbpopup.getId()).sequence(nowSequence).build());
            //다시 수정하고 리턴
            return update(TbpopupDto.TbpopupUpdateDto.builder().id(tbpopup.getId()).sequence(targetSequence).build());
        }
    }

    /**/

    public TbpopupDto.TbpopupAfterCreateDto create(TbpopupDto.TbpopupCreateDto params){
        params.setSequence(tbpopupMapper.pagedListCount(new TbpopupDto.TbpopupPagedListDto()) + 1);
        return tbpopupRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbpopupDto.TbpopupAfterUpdateDto update(TbpopupDto.TbpopupUpdateDto params){
        Tbpopup tbpopup = tbpopupRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getTitle() != null){
            tbpopup.setTitle(params.getTitle());
        }
        if(params.getSequence() != null){
            tbpopup.setSequence(params.getSequence());
        }
        if(params.getCate() != null){
            tbpopup.setCate(params.getCate());
        }
        if(params.getMpic() != null){
            tbpopup.setMpic(params.getMpic());
        }
        if(params.getUrl() != null){
            tbpopup.setUrl(params.getUrl());
        }
        if(params.getContent() != null){
            tbpopup.setContent(params.getContent());
        }
        if(params.getDeleted() != null){
            tbpopup.setDeleted(params.getDeleted());
        }
        tbpopupRepository.save(tbpopup);
        return tbpopup.toAfterUpdateDto();
    }
    public TbpopupDto.TbpopupAfterUpdateDto delete(TbpopupDto.TbpopupUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbpopupDto.TbpopupUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbpopupDto.TbpopupSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbpopupDto.TbpopupSelectDto get(String id){
        return tbpopupMapper.detail(id);
    }
    public List<TbpopupDto.TbpopupSelectDto> list(TbpopupDto.TbpopupListDto params){
        return addListDetails(tbpopupMapper.list(params));
    }
    public List<TbpopupDto.TbpopupSelectDto> moreList(TbpopupDto.TbpopupMoreListDto params){
        params.afterBuild();
        return addListDetails(tbpopupMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbpopupDto.TbpopupSelectDto> pagedList(TbpopupDto.TbpopupPagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbpopupMapper.pagedListCount(params)), addListDetails(tbpopupMapper.pagedList(params)));
    }

    public List<TbpopupDto.TbpopupSelectDto> addListDetails(List<TbpopupDto.TbpopupSelectDto> a_list){
        List<TbpopupDto.TbpopupSelectDto> result_list = new ArrayList<>();
        for(TbpopupDto.TbpopupSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
