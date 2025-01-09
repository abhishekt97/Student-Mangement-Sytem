package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.Question;
import com.abhishek.sms.entity.concretes.academic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubjectsName(String subjectName);
}
