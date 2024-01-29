package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbuser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByUsername(String username);

    // 로그인 기능 모두 구현하고 쓸것!! 나중에 확인합시다!!
    // 최초 조회 시 조인이 같이 하기위해 쓰는 어노테이션
    // 꼭 조인할때만 쓸것!
    @EntityGraph(attributePaths = {"tbuserRoleType.roleType"})
    Optional<Tbuser> findEntityGraphRoleTypeById(String id);
}
