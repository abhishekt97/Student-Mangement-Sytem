package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamRequest;
import com.abhishek.sms.payload.response.academic.ExamResponse;
import com.abhishek.sms.service.academic.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/exam")
@RestController
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/name/{name}")
    public ResponseEntity<ExamResponse> getExam(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(examService.getExam(name), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ExamResponse>> getAllExams(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return new ResponseEntity<>(examService.getAllExams(page, size), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Exam> addExam(@RequestBody ExamRequest examRequest){
        return new ResponseEntity<>(examService.addExam(examRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Exam> addExam(@RequestBody ExamRequest request,
                                        @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(examService.updateExam(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteExam(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(examService.deleteExam(name), HttpStatus.NO_CONTENT);
    }

}
