package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbcrewtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbcrewtimeRepository extends JpaRepository<Tbcrewtime, String> {
    Tbcrewtime findBySequence(Integer sequence);
}
