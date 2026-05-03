package com.yungwa.domain.lovetype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(
    name = "type_compatibilities",
    uniqueConstraints = @UniqueConstraint(columnNames = {"from_type_id", "to_type_id"})
)
public class TypeCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_type_id", nullable = false)
    private LoveType fromType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_type_id", nullable = false)
    private LoveType toType;

    @Builder
    public TypeCompatibility(LoveType fromType, LoveType toType) {
        this.fromType = fromType;
        this.toType = toType;
    }
}
