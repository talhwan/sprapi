package com.thc.sprapi.repository;

import com.thc.sprapi.domain.RefreshToken;
import com.thc.sprapi.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByContent(String Content);
    List<RefreshToken> findByTbuserId(String TbuserId);
}
