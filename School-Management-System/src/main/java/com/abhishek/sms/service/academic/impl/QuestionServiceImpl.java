package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.Question;
import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.QuestionRequest;
import com.abhishek.sms.payload.response.academic.QuestionResponse;
import com.abhishek.sms.repository.academic.QuestionRepository;
import com.abhishek.sms.repository.academic.SubjectRepository;
import com.abhishek.sms.repository.user.TeacherRepository;
import com.abhishek.sms.service.academic.QuestionService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.mapper.QuestionMapper;
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
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final PageUtil pageUtil;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final QuestionMapper mapper;

    @Override
    public QuestionResponse getQuestion(Long id) throws ResourceNotFoundException {
        Question question = getQuestionById(id);
        return mapper.mapQuestionToQuestionResponse(question);
    }

    @Override
    public Page<QuestionResponse> getQuestionsForSubject(String subjectName, int page, int size) throws ResourceNotFoundException {
        Pageable pageable = pageUtil.getPageableWithProperties(page, size);

        Subject subject = getSubjectForQuestion(subjectName);

        List<Question> questionList = questionRepository.findBySubjectName(subject.getName());
        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question q : questionList){
            questionResponseList.add(mapper.mapQuestionToQuestionResponse(q));
        }

        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), questionResponseList.size());
        List<QuestionResponse> pageQuestionResponse = questionResponseList.subList(start, end);
        return new PageImpl<>(pageQuestionResponse, pageable, questionResponseList.size());
    }

    @Override
    public Question addQuestion(QuestionRequest questionRequest) throws ResourceNotFoundException {

        Subject subject = getSubjectForQuestion(questionRequest.getSubjectName());

        Teacher teacher = getTeacher(questionRequest);

        return Question.builder()
                .problem(questionRequest.getProblem())
                .optionA(questionRequest.getOptionA())
                .optionB(questionRequest.getOptionB())
                .optionC(questionRequest.getOptionC())
                .optionD(questionRequest.getOptionD())
                .correctAnswer(questionRequest.getCorrectAnswer())
                .createdBy(teacher)
                .isCorrect(questionRequest.getIsCorrect())
                .subject(subject)
                .build();
    }



    @Override
    public QuestionResponse updateQuestion(Long id, QuestionRequest questionRequest) throws ResourceNotFoundException {
        Question question = getQuestionById(id);
        Subject subject = getSubjectForQuestion(questionRequest.getSubjectName());
        Teacher teacher = getTeacher(questionRequest);

        question.setProblem(questionRequest.getProblem());
        question.setCorrect(questionRequest.getIsCorrect());
        question.setOptionA(questionRequest.getOptionA());
        question.setOptionB(questionRequest.getOptionB());
        question.setOptionC(questionRequest.getOptionC());
        question.setOptionD(questionRequest.getOptionD());
        question.setCreatedBy(teacher);
        question.setSubject(subject);
        question.setCorrectAnswer(questionRequest.getCorrectAnswer());

        Question updatedQuestion = questionRepository.save(question);
        return mapper.mapQuestionToQuestionResponse(updatedQuestion);
    }

    @Override
    public String deleteQuestion(Long id) throws ResourceNotFoundException {
        Question questionById = getQuestionById(id);
        questionRepository.delete(questionById);
        return SuccessMessages.QUESTION_DELETED;
    }

    private Question getQuestionById(Long id) throws ResourceNotFoundException {
        return questionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_QUESTION, id))
        );
    }
    private Subject getSubjectForQuestion(String questionRequest) throws ResourceNotFoundException {
        return subjectRepository.findByName(questionRequest).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_SUBJECT, questionRequest)));
    }

    private Teacher getTeacher(QuestionRequest questionRequest) throws ResourceNotFoundException {
        return teacherRepository.findByFirstNameAndLastName(questionRequest.getCreatedByTeacherFirstName(),
                questionRequest.getCreatedByTeacherLastName()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TEACHER_BY_NAME, questionRequest.getCreatedByTeacherLastName() + " " + questionRequest.getCreatedByTeacherLastName())));
    }
}
