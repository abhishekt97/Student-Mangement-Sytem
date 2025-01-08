package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ExamResultRequest;
import com.abhishek.sms.payload.response.academic.ExamResultResponse;
import com.abhishek.sms.service.academic.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examResult")
@RequiredArgsConstructor
public class ExamResultController {
    
    private final ExamResultService examResultService;

    @GetMapping("/name/{id}")
    public ResponseEntity<ExamResultResponse> getExamResult(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(examResultService.getExamResult(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ExamResultResponse>> getAllExamResults(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return new ResponseEntity<>(examResultService.getAllExamResults(page, size), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ExamResult> addExamResult(@RequestBody ExamResultRequest examResultRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(examResultService.addExamResult(examResultRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExamResult> updateExamResult(@RequestBody ExamResultRequest examResultRequest,
                                        @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(examResultService.updateExamResult(id, examResultRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExamResult(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(examResultService.deleteExam(id), HttpStatus.NO_CONTENT);
    }
}
