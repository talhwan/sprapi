package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbgrantpart;
import com.thc.sprapi.domain.Tbgrantuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbgrantuserRepository extends JpaRepository<Tbgrantuser, String> {
    Tbgrantuser findByTbgrantIdAndTbuserId(String tbgrantId, String tbuserId);
}
