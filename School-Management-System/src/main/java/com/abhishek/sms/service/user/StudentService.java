package com.abhishek.sms.service.user;

import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.StudentRequest;
import com.abhishek.sms.payload.response.user.StudentResponse;

import java.util.List;

public interface StudentService {
    public List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id) throws ResourceNotFoundException;

    Student addStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest) throws ResourceNotFoundException;

    String deleteStudent(Long id) throws ResourceNotFoundException;
}
