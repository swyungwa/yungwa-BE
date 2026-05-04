package com.yungwa.domain.unlock.repository;

import com.yungwa.domain.unlock.entity.AccessLog;
import com.yungwa.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    boolean existsByViewerAndTarget(User viewer, User target);

    @Query("SELECT a FROM AccessLog a JOIN FETCH a.target t LEFT JOIN FETCH t.loveType WHERE a.viewer = :viewer ORDER BY a.accessedAt DESC")
    List<AccessLog> findByViewerWithTargetOrderByAccessedAtDesc(@Param("viewer") User viewer);
}
