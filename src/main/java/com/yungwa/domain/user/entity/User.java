package com.yungwa.domain.user.entity;

import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.user.domain.Gender;
import com.yungwa.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String instagramId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String mbti;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "love_type_id")
    private LoveType loveType;

    @Column(length = 255)
    private String introduction;

    private String emoji;

    @Column(nullable = false)
    private int ticketCount;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    public User(String instagramId, String password, Gender gender, String mbti,
            String introduction, String emoji) {
        this.instagramId = instagramId;
        this.password = password;
        this.gender = gender;
        this.mbti = mbti;
        this.introduction = introduction;
        this.emoji = emoji;
        this.ticketCount = 0;
        this.deleted = false;
    }

    public void updateLoveType(LoveType loveType) {
        this.loveType = loveType;
    }

    public void updateTicketCount(int delta) {
        this.ticketCount += delta;
    }
}
