package com.abhishek.sms.repository.user;

import com.abhishek.sms.entity.concretes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.roleType.roleText = :role")
    Page<User> findByUserByRole(@Param("role") String role, Pageable pageable);

    User findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM User u WHERE u.roleType.roleText = TEACHER")
    List<User> findAllTeachers();
}
