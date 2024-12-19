package com.abhishek.sms.repository.user;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    long count();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findByIsSuspendedTrue();
    List<Student> findByProgram(Program program);
    List<Student> findByClassLevel(ClassLevel classLevel);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);

}
