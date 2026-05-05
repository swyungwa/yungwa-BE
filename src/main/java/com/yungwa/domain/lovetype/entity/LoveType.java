package com.yungwa.domain.lovetype.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "love_types")
public class LoveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_code", unique = true, nullable = false, length = 20)
    private LoveTypeCode typeCode;

    @Column(name = "name_ko", nullable = false, length = 20)
    private String nameKo;

    @Column(nullable = false, length = 100)
    private String description;

    @Builder
    public LoveType(LoveTypeCode typeCode, String nameKo, String description) {
        this.typeCode = typeCode;
        this.nameKo = nameKo;
        this.description = description;
    }
}
