package com.abhishek.sms.utils.mapper;

import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.payload.response.academic.SubjectResponse;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectResponse mapSubjectToSubjectResponse(Subject subject){

        return SubjectResponse.builder()
                .name(subject.getName())
                .description(subject.getDescription())
                .teacherName(subject.getTeacher().getFirstName() + " " + subject.getTeacher().getLastName())
                .duration(subject.getDuration())
                .build();
    }
}
