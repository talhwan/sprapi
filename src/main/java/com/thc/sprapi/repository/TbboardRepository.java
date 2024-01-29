package com.thc.sprapi.repository;

import com.thc.sprapi.domain.Tbboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbboardRepository extends JpaRepository<Tbboard, String> {
    /*
    TbboardRepository를 사용하기 위해,
    JpaRepository<Tbboard, String>에서
    Tbboard 라는 엔티티 클래스를 먼저 제공하고, Tbboard의 인덱스pk의 자료형을 선언해줍니다.

    기존에 이미 구현된 JPA 기능을 모두 사용 가능!!
    */
}
