package com.abhishek.sms.utils.mapper;

import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.payload.response.academic.ExamResultResponse;
import org.springframework.stereotype.Component;

@Component
public class ExamResultMapper {


    public ExamResultResponse mapExamResultToExamResultResponse(ExamResult examResult){

        return ExamResultResponse.builder()
                .studentName(examResult.getStudent().getFirstName() + " " + examResult.getStudent().getLastName())
                .examName(examResult.getExam().getName())
                .score(examResult.getScore())
                .remark(examResult.getRemark())
                .build();
    }
}
