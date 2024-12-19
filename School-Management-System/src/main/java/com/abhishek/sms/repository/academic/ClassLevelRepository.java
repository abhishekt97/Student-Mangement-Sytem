package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassLevelRepository extends JpaRepository<ClassLevel, Long> {

    Optional<ClassLevel> findByName(String classLevel);


}
