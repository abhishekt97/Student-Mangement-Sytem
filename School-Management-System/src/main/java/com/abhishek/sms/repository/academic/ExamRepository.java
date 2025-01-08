package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findByName(String name);

    boolean existsByName(String name);
}
