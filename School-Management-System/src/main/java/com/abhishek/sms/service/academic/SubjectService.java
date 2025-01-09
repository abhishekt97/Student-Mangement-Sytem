package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.SubjectRequest;
import com.abhishek.sms.payload.response.academic.SubjectResponse;

public interface SubjectService {
    SubjectResponse getSubject(String name) throws ResourceNotFoundException;

    SubjectResponse getSubjectByTeacher(Long id) throws ResourceNotFoundException;

    Subject addSubject(SubjectRequest subjectRequest) throws ResourceNotFoundException;

    Subject updateSubject(String name, SubjectRequest subjectRequest) throws ResourceNotFoundException;

    String  deleteSubject(String name) throws ResourceNotFoundException;
}
