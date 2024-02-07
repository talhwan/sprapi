package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbgsquiduser;
import com.thc.sprapi.domain.Tbgsquidushot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbgsquidushotRepository extends JpaRepository<Tbgsquidushot, String> {
    Tbgsquidushot findByTbgsquiduserIdAndLevel(String tbgsquiduserId, String level);
}
