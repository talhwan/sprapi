package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbgrantpart;
import com.thc.sprapi.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbgrantpartRepository extends JpaRepository<Tbgrantpart, String> {
    Tbgrantpart findByTbgrantIdAndTypeAndContent(String tbgrantId, String type, String content);
}
