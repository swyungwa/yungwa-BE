package com.yungwa.domain.lovetype.repository;

import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.lovetype.entity.LoveTypeCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoveTypeRepository extends JpaRepository<LoveType, Long> {

    Optional<LoveType> findByTypeCode(LoveTypeCode typeCode);
}
