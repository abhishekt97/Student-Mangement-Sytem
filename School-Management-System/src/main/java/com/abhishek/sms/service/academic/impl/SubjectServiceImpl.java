package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.SubjectRequest;
import com.abhishek.sms.payload.response.academic.SubjectResponse;
import com.abhishek.sms.repository.academic.AcademicYearRepository;
import com.abhishek.sms.repository.academic.ClassLevelRepository;
import com.abhishek.sms.repository.academic.SubjectRepository;
import com.abhishek.sms.repository.user.TeacherRepository;
import com.abhishek.sms.service.academic.SubjectService;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.mapper.SubjectMapper;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper mapper;
    private final Validator validator;
    private final TeacherRepository teacherRepository;
    private final ClassLevelRepository classLevelRepository;
    private final AcademicYearRepository academicYearRepository;
    @Override
    public SubjectResponse getSubject(String name) throws ResourceNotFoundException {
        Subject subject = getSubjectByName(name);
        return mapper.mapSubjectToSubjectResponse(subject);
    }

    @Override
    public SubjectResponse getSubjectByTeacher(Long id) throws ResourceNotFoundException {

        Subject subject = subjectRepository.findByTeacherId(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_SUBJECT_BY_TEACHER_ID, id)));

        return mapper.mapSubjectToSubjectResponse(subject);
    }

    @Override
    public Subject addSubject(SubjectRequest subjectRequest) throws ResourceNotFoundException {
        validator.checkDuplicateSubject(subjectRequest.getName());

        return Subject.builder()
                .name(subjectRequest.getName())
                .description(subjectRequest.getDescription())
                .duration(subjectRequest.getDuration())
                .classLevel(getClassLevel(subjectRequest))
                .teacher(getTeacher(subjectRequest))
                .academicYear(getAcademicYear(subjectRequest))
                .build();
    }

    @Override
    public Subject updateSubject(String name, SubjectRequest subjectRequest) throws ResourceNotFoundException {
        Subject subject = getSubjectByName(name);

        subject.setName(subjectRequest.getName());
        subject.setDescription(subjectRequest.getDescription());
        subject.setDuration(subjectRequest.getDuration());
        subject.setTeacher(getTeacher(subjectRequest));
        subject.setClassLevel(getClassLevel(subjectRequest));
        subject.setAcademicYear(getAcademicYear(subjectRequest));

        return subjectRepository.save(subject);
    }

    @Override
    public String deleteSubject(String name) throws ResourceNotFoundException {
        Subject subject = getSubjectByName(name);
        subjectRepository.delete(subject);
        return SuccessMessages.SUBJECT_DELETED;
    }

    private Subject getSubjectByName(String name) throws ResourceNotFoundException {
        return subjectRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_SUBJECT, name)));
    }

    private AcademicYear getAcademicYear(SubjectRequest subjectRequest) throws ResourceNotFoundException {
        return academicYearRepository.findByName(subjectRequest.getAcademicYear()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ACADEMIC_YEAR, subjectRequest.getAcademicYear())));
    }

    private ClassLevel getClassLevel(SubjectRequest subjectRequest) throws ResourceNotFoundException {
        return classLevelRepository.findByName(subjectRequest.getClassLevel()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CLASS_LEVEL, subjectRequest.getClassLevel())));
    }

    private Teacher getTeacher(SubjectRequest subjectRequest) throws ResourceNotFoundException {
        return teacherRepository.findByFirstNameAndLastName(subjectRequest.getTeacherFirstName(), subjectRequest.getTeacherLastName())
                .orElseThrow(() -> new ResourceNotFoundException
                        (String.format(ErrorMessages.NOT_FOUND_TEACHER_BY_NAME, subjectRequest.getTeacherFirstName() + " " + subjectRequest.getTeacherLastName())));
    }

}
