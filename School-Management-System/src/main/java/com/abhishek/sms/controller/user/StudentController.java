package com.abhishek.sms.controller.user;

import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.StudentRequest;
import com.abhishek.sms.payload.response.user.StudentResponse;
import com.abhishek.sms.service.user.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(studentService.addStudent(studentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@Valid @RequestBody StudentRequest studentRequest,
                                                         @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.NO_CONTENT);
    }



}
