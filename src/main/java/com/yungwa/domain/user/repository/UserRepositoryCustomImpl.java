package com.yungwa.domain.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yungwa.domain.user.domain.Gender;
import com.yungwa.domain.user.domain.GenderFilter;
import com.yungwa.domain.user.entity.QUser;
import com.yungwa.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> findCardsByGenderFilter(Long viewerId, GenderFilter genderFilter) {
        QUser user = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        if (viewerId != null) {
            builder.and(user.id.ne(viewerId));
        }
        builder.and(user.deleted.isFalse());

        if (genderFilter != GenderFilter.ALL) {
            builder.and(user.gender.eq(Gender.valueOf(genderFilter.name())));
        }

        return queryFactory
                .selectFrom(user)
                .leftJoin(user.loveType).fetchJoin()
                .where(builder)
                .fetch();
    }
}
