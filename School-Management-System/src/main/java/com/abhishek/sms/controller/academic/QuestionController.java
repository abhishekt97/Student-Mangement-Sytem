package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.entity.concretes.academic.Question;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamRequest;
import com.abhishek.sms.payload.request.academic.QuestionRequest;
import com.abhishek.sms.payload.response.academic.ExamResponse;
import com.abhishek.sms.payload.response.academic.QuestionResponse;
import com.abhishek.sms.service.academic.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/id/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(questionService.getQuestion(id), HttpStatus.OK);
    }

    @GetMapping("/subject/{subjectName}")
    public ResponseEntity<Page<QuestionResponse>> getQuestionsForSubject(
            @PathVariable String subjectName,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) throws ResourceNotFoundException {
        return new ResponseEntity<>(questionService.getQuestionsForSubject(subjectName, page, size), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionRequest questionRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(questionService.addQuestion(questionRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@RequestBody QuestionRequest questionRequest,
                                        @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(questionService.updateQuestion(id, questionRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.NO_CONTENT);
    }
}
