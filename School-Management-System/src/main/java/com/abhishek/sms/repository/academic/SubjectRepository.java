package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
