package com.thc.sprapi.repository;

import com.thc.sprapi.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thc.sprapi.domain.TbuserRoleType;

import java.util.List;

public interface TbuserRoleTypeRepository extends JpaRepository<TbuserRoleType, String> {
}