package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamResultRequest;
import com.abhishek.sms.payload.response.academic.ExamResultResponse;
import org.springframework.data.domain.Page;

public interface ExamResultService {

    ExamResultResponse getExamResult(Long id) throws ResourceNotFoundException;

    Page<ExamResultResponse> getAllExamResults(int page, int size);

    ExamResult addExamResult(ExamResultRequest examResultRequest) throws ResourceNotFoundException;

    ExamResult updateExamResult(Long id, ExamResultRequest examResultRequest) throws ResourceNotFoundException;

    String deleteExam(Long id) throws ResourceNotFoundException;
}
