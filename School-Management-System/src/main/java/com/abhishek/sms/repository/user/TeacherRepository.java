package com.abhishek.sms.repository.user;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.entity.concretes.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
    List<Teacher> findByClassLevel(ClassLevel classLevel);

    List<Teacher> findAll();
    List<Teacher> findAllByOrderByIdAsc();
    List<Teacher> findTop10ByOrderByDateEmployedAsc();
    boolean existsByEmail(String email);
    List<Teacher> findByIsSuspendedTrue();
    Optional<Teacher> findBySubject(Subject subject);
    Optional<Teacher> findByExamsCreated(Exam exam);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteTeacherById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);
}
