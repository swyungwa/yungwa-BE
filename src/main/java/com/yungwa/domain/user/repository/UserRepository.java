package com.yungwa.domain.user.repository;

import com.yungwa.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByInstagramId(String instagramId);

    Optional<User> findByInstagramId(String instagramId);
}
