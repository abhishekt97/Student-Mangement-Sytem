package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.Question;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.QuestionRequest;
import com.abhishek.sms.payload.response.academic.QuestionResponse;
import org.springframework.data.domain.Page;

public interface QuestionService {

    QuestionResponse getQuestion(Long id) throws ResourceNotFoundException;

    Page<QuestionResponse> getQuestionsForSubject(String subjectName, int page, int size) throws ResourceNotFoundException;

    Question addQuestion(QuestionRequest questionRequest) throws ResourceNotFoundException;

    QuestionResponse updateQuestion(Long id, QuestionRequest questionRequest) throws ResourceNotFoundException;

    String  deleteQuestion(Long id) throws ResourceNotFoundException;
}
