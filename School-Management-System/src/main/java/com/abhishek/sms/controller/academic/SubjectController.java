package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.SubjectRequest;
import com.abhishek.sms.payload.response.academic.SubjectResponse;
import com.abhishek.sms.service.academic.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/name/{name}")
    public ResponseEntity<SubjectResponse> getSubject(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.getSubject(name), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<SubjectResponse> getSubjectByTeacher(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.getSubjectByTeacher(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> addExam(@RequestBody SubjectRequest subjectRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.addSubject(subjectRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Subject> addExam(@RequestBody SubjectRequest subjectRequest,
                                        @PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.updateSubject(name, subjectRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteExam(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(subjectService.deleteSubject(name), HttpStatus.NO_CONTENT);
    }
}
