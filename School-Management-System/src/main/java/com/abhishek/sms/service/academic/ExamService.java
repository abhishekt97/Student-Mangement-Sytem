package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamRequest;
import com.abhishek.sms.payload.response.academic.ExamResponse;
import org.springframework.data.domain.Page;

public interface ExamService {
    ExamResponse getExam(String name) throws ResourceNotFoundException;

    Page<ExamResponse> getAllExams(int page, int size);

    Exam addExam(ExamRequest examRequest);

    Exam updateExam(Long id, ExamRequest request) throws ResourceNotFoundException;

    String deleteExam(String name) throws ResourceNotFoundException;
}
