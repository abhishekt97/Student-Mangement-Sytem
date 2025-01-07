package com.abhishek.sms.service.user.impl;

import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.StudentRequest;
import com.abhishek.sms.payload.response.user.StudentResponse;
import com.abhishek.sms.repository.user.StudentRepository;
import com.abhishek.sms.service.user.StudentService;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;
    private final Validator validator;
    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> all = studentRepository.findAll();
        List<StudentResponse> responseList = new ArrayList<>();
        
        for(Student s : all){
            responseList.add(mapper.map(s, StudentResponse.class));
        }
        return responseList;
    }

    @Override
    public StudentResponse getStudentById(Long id) throws ResourceNotFoundException {

        Student student = isStudentExist(id);
        return mapper.map(student, StudentResponse.class);
    }

    @Override
    public Student addStudent(StudentRequest studentRequest) {

        validator.checkDuplicateStudent(studentRequest.getUsername(),
                                        studentRequest.getEmail(),
                                        studentRequest.getPhoneNumber());

        Student student = mapper.map(studentRequest, Student.class);
        return studentRepository.save(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) throws ResourceNotFoundException {
        Student student = isStudentExist(id);
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setParent(studentRequest.getParent());
        student.setUsername(studentRequest.getUsername());
        student.setAcademicYear(studentRequest.getAcademicYear());
        student.setClassLevel(studentRequest.getClassLevel());
        student.setGender(studentRequest.getGender());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
        student.setProgram(studentRequest.getProgram());
        student.setExamResult(studentRequest.getExamResult());

        Student savedStudent = studentRepository.save(student);

        return mapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public String deleteStudent(Long id) throws ResourceNotFoundException {
        Student student = isStudentExist(id);
        studentRepository.delete(student);
        return SuccessMessages.STUDENT_DELETED;
    }

    private Student isStudentExist(Long id) throws ResourceNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_STUDENT_MESSAGE, id)));
    }

}
