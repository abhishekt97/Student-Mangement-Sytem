package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    Optional<Subject> findByTeacherId(Long id);

    boolean existsByName(String name);
}
