package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    Optional<Subject> findByTeachersId(Long id);

    boolean existsByName(String name);
}
