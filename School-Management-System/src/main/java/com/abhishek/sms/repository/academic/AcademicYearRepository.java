package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

    Optional<AcademicYear> findByName(String name);
}
