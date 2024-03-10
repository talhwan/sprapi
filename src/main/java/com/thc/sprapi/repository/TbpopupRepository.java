package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbpopup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbpopupRepository extends JpaRepository<Tbpopup, String> {
    Tbpopup findBySequence(Integer sequence);
}
