package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.Tbcrewtime;
import com.thc.sprapi.dto.CommonAfterPagedListDto;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.CommonDetailDto;
import com.thc.sprapi.dto.TbcrewtimeDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.mapper.TbcrewtimeMapper;
import com.thc.sprapi.repository.TbcrewtimeRepository;
import com.thc.sprapi.service.TbcrewtimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbcrewtimeServiceImpl implements TbcrewtimeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbcrewtimeRepository tbcrewtimeRepository;
    private final TbcrewtimeMapper tbcrewtimeMapper;
    public TbcrewtimeServiceImpl(
            TbcrewtimeRepository tbcrewtimeRepository
            ,TbcrewtimeMapper tbcrewtimeMapper
    ) {
        this.tbcrewtimeRepository = tbcrewtimeRepository;
        this.tbcrewtimeMapper = tbcrewtimeMapper;
    }

    public TbcrewtimeDto.TbcrewtimeSelectDto recommend(){
        TbcrewtimeDto.TbcrewtimeListDto params = TbcrewtimeDto.TbcrewtimeListDto.builder().deleted("N").build();
        return get(tbcrewtimeMapper.random(params).getId());
    }

    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto sequence(TbcrewtimeDto.TbcrewtimeSequenceDto params){

        Tbcrewtime tbcrewtime = tbcrewtimeRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        int nowSequence = tbcrewtime.getSequence();

        int targetSequence = nowSequence;
        if("up".equals(params.getWay())){
            targetSequence++;
        } else {
            targetSequence--;
        }
        if(targetSequence == 0 || targetSequence > tbcrewtimeMapper.pagedListCount(new TbcrewtimeDto.TbcrewtimePagedListDto())){
            return null;
            //throw new CommonException("");
        } else {
            //잠시 순번에서 제외
            update(TbcrewtimeDto.TbcrewtimeUpdateDto.builder().id(tbcrewtime.getId()).sequence(-1).build());
            //바꾸고자 하는 순번의 정보에 이전 순번 저장
            Tbcrewtime targetTbcrewtime = tbcrewtimeRepository.findBySequence(targetSequence);
            update(TbcrewtimeDto.TbcrewtimeUpdateDto.builder().id(targetTbcrewtime.getId()).sequence(nowSequence).build());
            //다시 수정하고 리턴
            return update(TbcrewtimeDto.TbcrewtimeUpdateDto.builder().id(tbcrewtime.getId()).sequence(targetSequence).build());
        }
    }

    /**/

    public TbcrewtimeDto.TbcrewtimeAfterCreateDto create(TbcrewtimeDto.TbcrewtimeCreateDto params){
        params.setSequence(tbcrewtimeMapper.pagedListCount(new TbcrewtimeDto.TbcrewtimePagedListDto()) + 1);
        return tbcrewtimeRepository.save(params.toEntity()).toAfterCreateDto();
    }
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto update(TbcrewtimeDto.TbcrewtimeUpdateDto params){
        Tbcrewtime tbcrewtime = tbcrewtimeRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchingDataException(""));
        if(params.getDeleted() != null){
            tbcrewtime.setDeleted(params.getDeleted());
        }
        if(params.getTitle() != null){
            tbcrewtime.setTitle(params.getTitle());
        }
        if(params.getSequence() != null){
            tbcrewtime.setSequence(params.getSequence());
        }
        if(params.getCate() != null){
            tbcrewtime.setCate(params.getCate());
        }

        if(params.getStartage() != null) {
            tbcrewtime.setStartage(params.getStartage());
        }
        if(params.getFinishage() != null) {
            tbcrewtime.setFinishage(params.getFinishage());
        }
        if(params.getDay0() != null) {
            tbcrewtime.setDay0(params.getDay0());
        }
        if(params.getDay1() != null) {
            tbcrewtime.setDay1(params.getDay1());
        }
        if(params.getDay2() != null) {
            tbcrewtime.setDay2(params.getDay2());
        }
        if(params.getDay3() != null) {
            tbcrewtime.setDay3(params.getDay3());
        }
        if(params.getDay4() != null) {
            tbcrewtime.setDay4(params.getDay4());
        }
        if(params.getDay5() != null) {
            tbcrewtime.setDay5(params.getDay5());
        }
        if(params.getDay6() != null) {
            tbcrewtime.setDay6(params.getDay6());
        }

        if(params.getStarttime() != null) {
            tbcrewtime.setStarttime(params.getStarttime());
        }
        if(params.getFinishtime() != null) {
            tbcrewtime.setFinishtime(params.getFinishtime());
        }

        tbcrewtimeRepository.save(tbcrewtime);
        return tbcrewtime.toAfterUpdateDto();
    }
    public TbcrewtimeDto.TbcrewtimeAfterUpdateDto delete(TbcrewtimeDto.TbcrewtimeUpdateDto params){
        params.setDeleted("Y");
        return update(params);
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(TbcrewtimeDto.TbcrewtimeUpdateDto.builder().id(each).build());
        }
        return params;
    }

    public TbcrewtimeDto.TbcrewtimeSelectDto detail(CommonDetailDto params){
        return get(params.getId());
    }
    public TbcrewtimeDto.TbcrewtimeSelectDto get(String id){
        return tbcrewtimeMapper.detail(id);
    }
    public List<TbcrewtimeDto.TbcrewtimeSelectDto> list(TbcrewtimeDto.TbcrewtimeListDto params){
        return addListDetails(tbcrewtimeMapper.list(params));
    }
    public List<TbcrewtimeDto.TbcrewtimeSelectDto> moreList(TbcrewtimeDto.TbcrewtimeMoreListDto params){
        params.afterBuild();
        return addListDetails(tbcrewtimeMapper.moreList(params));
    }
    public CommonAfterPagedListDto<TbcrewtimeDto.TbcrewtimeSelectDto> pagedList(TbcrewtimeDto.TbcrewtimePagedListDto params){
        return new CommonAfterPagedListDto<>(params.afterBuild(tbcrewtimeMapper.pagedListCount(params)), addListDetails(tbcrewtimeMapper.pagedList(params)));
    }

    public List<TbcrewtimeDto.TbcrewtimeSelectDto> addListDetails(List<TbcrewtimeDto.TbcrewtimeSelectDto> a_list){
        List<TbcrewtimeDto.TbcrewtimeSelectDto> result_list = new ArrayList<>();
        for(TbcrewtimeDto.TbcrewtimeSelectDto a : a_list){
            result_list.add(get(a.getId()));
        }
        return result_list;
    }

}
