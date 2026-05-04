package com.yungwa.domain.user.repository;

import com.yungwa.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    boolean existsByInstagramId(String instagramId);

    Optional<User> findByInstagramId(String instagramId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.loveType WHERE u.id = :id")
    Optional<User> findByIdWithLoveType(@Param("id") Long id);
}
