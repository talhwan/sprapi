package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbbanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbbannerRepository extends JpaRepository<Tbbanner, String> {
    Tbbanner findBySequence(Integer sequence);
}
