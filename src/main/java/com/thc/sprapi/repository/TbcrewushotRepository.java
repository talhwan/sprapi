package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbcrewushot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbcrewushotRepository extends JpaRepository<Tbcrewushot, String> {
    Tbcrewushot findByTbcrewuserIdAndLevel(String tbcrewuserId, String level);
}
