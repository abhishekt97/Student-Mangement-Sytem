package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

}
