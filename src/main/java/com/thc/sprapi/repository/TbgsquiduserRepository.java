package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbgsquiduser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbgsquiduserRepository extends JpaRepository<Tbgsquiduser, String> {
    Tbgsquiduser findByTbgsquidIdAndTbuserId(String tbgsquidId, String tbuserId);
}
