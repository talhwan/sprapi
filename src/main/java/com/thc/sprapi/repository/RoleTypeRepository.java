package com.thc.sprapi.repository;

import com.thc.sprapi.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleTypeRepository extends JpaRepository<RoleType, String>{
	RoleType findByTypeName(String typeName);

}
