package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.academic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    Optional<Program> findByName(String name);

    Optional<Program> findBySubjects(Subject subject);

    Optional<Program> findByStudentsUsername(String username);

    boolean existsByName(String name);
}
