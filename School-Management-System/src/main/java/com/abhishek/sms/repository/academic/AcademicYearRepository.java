package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

    Optional<AcademicYear> findByName(String name);
}
