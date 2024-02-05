package com.thc.sprapi.service.impl;

import com.thc.sprapi.domain.RoleType;
import com.thc.sprapi.domain.TbuserRoleType;
import com.thc.sprapi.dto.CommonDeleteListDto;
import com.thc.sprapi.dto.RoletypeDto;
import com.thc.sprapi.exception.NoMatchingDataException;
import com.thc.sprapi.repository.RoleTypeRepository;
import com.thc.sprapi.repository.TbuserRoleTypeRepository;
import com.thc.sprapi.service.RoletypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoletypeServiceImpl implements RoletypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoleTypeRepository roleTypeRepository;
    private final TbuserRoleTypeRepository tbuserRoleTypeRepository;
    public RoletypeServiceImpl(
            RoleTypeRepository roleTypeRepository
            ,TbuserRoleTypeRepository tbuserRoleTypeRepository
    ) {
        this.roleTypeRepository = roleTypeRepository;
        this.tbuserRoleTypeRepository = tbuserRoleTypeRepository;
    }

    public RoletypeDto.RoletypeSelectDto create(RoletypeDto.RoletypeCreateDto params){
        return roleTypeRepository.save(params.toEntity()).toRoletypeSelectDto();
    }
    public RoletypeDto.RoletypeSelectDto delete(String id){
        RoleType roleType = roleTypeRepository.findById(id)
                .orElseThrow(() -> new NoMatchingDataException(""));
        roleTypeRepository.delete(roleType);
        return roleType.toRoletypeSelectDto();
    }
    public CommonDeleteListDto deleteList(CommonDeleteListDto params){
        for(String each : params.getIds()){
            delete(each);
        }
        return params;
    }
    public RoletypeDto.RoletypeSelectDto detail(String id){
        RoleType roleType = roleTypeRepository.findById(id)
                .orElseThrow(() -> new NoMatchingDataException(""));
        RoletypeDto.RoletypeSelectDto returnVal = roleType.toRoletypeSelectDto();
        return returnVal;
    }
    public List<RoletypeDto.RoletypeSelectDto> list(){
        List<RoleType> listRoleType = roleTypeRepository.findAll();
        List<RoletypeDto.RoletypeSelectDto> resultList = new ArrayList<>();
        for(RoleType roleType : listRoleType){
            resultList.add(roleType.toRoletypeSelectDto());
        }
        return resultList;
    }
}
