package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamRequest;
import com.abhishek.sms.payload.response.academic.ExamResponse;
import com.abhishek.sms.repository.academic.ExamRepository;
import com.abhishek.sms.service.academic.ExamService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final PageUtil pageUtil;
    private final ModelMapper mapper;
    private final Validator validator;


    @Override
    public ExamResponse getExam(String name) throws ResourceNotFoundException {
        Exam exam = getExamByName(name);
        return mapper.map(exam, ExamResponse.class);
    }

    @Override
    public Page<ExamResponse> getAllExams(int page, int size) {
        Pageable pageable = pageUtil.getPageableWithProperties(page, size);
        List<Exam> examList = examRepository.findAll();
        List<ExamResponse> examResponseList = new ArrayList<>();
        for (Exam e : examList){
            examResponseList.add(mapper.map(e, ExamResponse.class));
        }
        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), examList.size());
        List<ExamResponse> examResponsePage = examResponseList.subList(start, end);

        return new PageImpl<>(examResponsePage, pageable, examResponseList.size());
    }

    @Override
    public Exam addExam(ExamRequest examRequest) {
        validator.checkDuplicateExam(examRequest.getName());
        Exam newExam = mapper.map(examRequest, Exam.class);
        return examRepository.save(newExam);
    }

    @Override
    public Exam updateExam(Long id, ExamRequest request) throws ResourceNotFoundException {
        Exam exam = examRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_EXAM_ID, id))
        );
        exam.setExamDate(request.getExamDate());
        exam.setExamType(request.getExamType());
        exam.setDescription(request.getDescription());
        exam.setAcademicYear(request.getAcademicYear());
        exam.setDuration(request.getDuration());
        exam.setClassLevel(request.getClassLevel());
        exam.setQuestions(request.getQuestions());
        exam.setSubject(request.getSubject());
        exam.setCreatedBy(request.getCreatedBy());
        exam.setPassMarks(request.getPassMarks());
        exam.setExamType(request.getExamType());
        exam.setPassMarks(request.getPassMarks());
        exam.setTotalMarks(request.getTotalMarks());

        return examRepository.save(exam);
    }

    @Override
    public String deleteExam(String name) throws ResourceNotFoundException {
        Exam examByName = getExamByName(name);
        examRepository.delete(examByName);
        return SuccessMessages.EXAM_DELETED;
    }

    private Exam getExamByName(String name) throws ResourceNotFoundException {
        return examRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_EXAM, name))
        );
    }
}
