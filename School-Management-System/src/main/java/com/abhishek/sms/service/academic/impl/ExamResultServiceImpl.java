package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamResultRequest;
import com.abhishek.sms.payload.response.academic.ExamResultResponse;
import com.abhishek.sms.repository.academic.ExamRepository;
import com.abhishek.sms.repository.academic.ExamResultRepository;
import com.abhishek.sms.repository.user.StudentRepository;
import com.abhishek.sms.service.academic.ExamResultService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.mapper.ExamResultMapper;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final PageUtil pageUtil;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ExamResultMapper mapper;

    @Override
    public ExamResultResponse getExamResult(Long id) throws ResourceNotFoundException {
        ExamResult examResult = getResult(id);
        return mapper.mapExamResultToExamResultResponse(examResult);
    }

    @Override
    public Page<ExamResultResponse> getAllExamResults(int page, int size) {
        List<ExamResult> examResultList = examResultRepository.findAll();
        List<ExamResultResponse> examResultResponseList = new ArrayList<>();
        for(ExamResult e : examResultList){
            examResultResponseList.add(mapper.mapExamResultToExamResultResponse(e));
        }

        Pageable pageable = pageUtil.getPageableWithProperties(page, size);
        int start =(int)pageable.getOffset();
        int end = Math.min( start + pageable.getPageSize(), examResultResponseList.size());
        List<ExamResultResponse> pageExamResultResponseList = examResultResponseList.subList(start, end);

        return new PageImpl<>(pageExamResultResponseList, pageable, examResultResponseList.size());
    }

    @Override
    public ExamResult addExamResult(ExamResultRequest examResultRequest) throws ResourceNotFoundException {
        return ExamResult.builder()
                .exam(getExam(examResultRequest))
                .score(examResultRequest.getScore())
                .student(getStudent(examResultRequest))
                .status(examResultRequest.getStatus())
                .isPublished(examResultRequest.getIsPublished())
                .remark(examResultRequest.getRemark())
                .build();
    }


    @Override
    public ExamResult updateExamResult(Long id, ExamResultRequest examResultRequest) throws ResourceNotFoundException {
        ExamResult result = getResult(id);

        result.setExam(getExam(examResultRequest));
        result.setPublished(examResultRequest.getIsPublished());
        result.setRemark(examResultRequest.getRemark());
        result.setStatus(examResultRequest.getStatus());
        result.setScore(examResultRequest.getScore());
        result.setStudent(getStudent(examResultRequest));

        return examResultRepository.save(result);
    }

    @Override
    public String deleteExam(Long id) throws ResourceNotFoundException {
        ExamResult result = getResult(id);
        examResultRepository.delete(result);
        return SuccessMessages.EXAM_RESULT_DELETED;
    }

    private Exam getExam(ExamResultRequest examResultRequest) throws ResourceNotFoundException {
        return examRepository.findByName(examResultRequest.getExamName()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_EXAM, examResultRequest.getExamName())));
    }

    private Student getStudent(ExamResultRequest examResultRequest) throws ResourceNotFoundException {

        String studentName = examResultRequest.getStudentFirstName() + " " + examResultRequest.getStudentLastName();

        return studentRepository.findByFirstNameAndLastName(examResultRequest.getStudentFirstName(),
                examResultRequest.getStudentLastName()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.STUDENT_INFO_NOT_FOUND_BY_NAME, studentName)));
    }

    private ExamResult getResult(Long id) throws ResourceNotFoundException {
        return examResultRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_EXAM_RESULT, id))
        );
    }

}
