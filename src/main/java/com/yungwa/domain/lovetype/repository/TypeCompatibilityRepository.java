package com.yungwa.domain.lovetype.repository;

import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.lovetype.entity.TypeCompatibility;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCompatibilityRepository extends JpaRepository<TypeCompatibility, Long> {

    Optional<TypeCompatibility> findByFromTypeAndToType(LoveType fromType, LoveType toType);
}
