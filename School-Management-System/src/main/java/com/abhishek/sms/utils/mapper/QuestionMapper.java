package com.abhishek.sms.utils.mapper;

import com.abhishek.sms.entity.concretes.academic.Question;
import com.abhishek.sms.payload.response.academic.QuestionResponse;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public QuestionResponse mapQuestionToQuestionResponse(Question question){
        return QuestionResponse.builder()
                .createdByTeacher(question.getCreatedBy().getFirstName()+ " "+ question.getCreatedBy().getLastName())
                .subjectName(question.getSubject().getName())
                .problem(question.getProblem())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .build();
    }
}
