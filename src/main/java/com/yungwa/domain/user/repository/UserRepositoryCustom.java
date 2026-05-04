package com.yungwa.domain.user.repository;

import com.yungwa.domain.user.domain.GenderFilter;
import com.yungwa.domain.user.entity.User;
import java.util.List;

public interface UserRepositoryCustom {

    List<User> findCardsByGenderFilter(Long viewerId, GenderFilter genderFilter);
}
