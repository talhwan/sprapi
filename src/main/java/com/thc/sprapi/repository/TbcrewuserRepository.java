package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbcrewuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbcrewuserRepository extends JpaRepository<Tbcrewuser, String> {
    Tbcrewuser findByTbcrewIdAndTbuserId(String tbcrewId, String tbuserId);
}
