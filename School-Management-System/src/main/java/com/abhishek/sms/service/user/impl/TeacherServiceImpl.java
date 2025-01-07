package com.abhishek.sms.service.user.impl;

import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.TeacherRequest;
import com.abhishek.sms.payload.response.user.TeacherResponse;
import com.abhishek.sms.repository.user.TeacherRepository;
import com.abhishek.sms.service.user.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Override
    public List<TeacherResponse> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponse> responseList = new ArrayList<>();

        for( Teacher t : teachers){
            responseList.add(mapper.map(t, TeacherResponse.class));
        }
        return responseList;
    }

    @Override
    public Teacher saveTeacher(TeacherRequest teacherRequest) {
        validator.checkDuplicateTeacher(teacherRequest.getUsername(),
                                        teacherRequest.getEmail(),
                                        teacherRequest.getPhoneNumber());
        Teacher newTeacher = mapper.map(teacherRequest, Teacher.class);
        return teacherRepository.save(newTeacher);
    }

    @Override
    public String deleteTeacherById(Long id) throws ResourceNotFoundException {
        Teacher teacher = getTeacherById(id);

        teacherRepository.delete(teacher);

        return SuccessMessages.TEACHER_DELETED;
    }



    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) throws ResourceNotFoundException {
        Teacher teacher = getTeacherById(id);

        teacher.setDateEmployed( teacherRequest.getDateEmployed());
        teacher.setEmail( teacherRequest.getEmail());
        teacher.setPassword(  teacherRequest.getPassword());
        teacher.setUsername( teacherRequest.getUsername());
        teacher.setGender( teacherRequest.getGender());
        teacher.setAge( teacherRequest.getAge());
        teacher.setFirstName( teacherRequest.getFirstName());
        teacher.setPassword( teacherRequest.getPhoneNumber());
        teacher.setLastName( teacherRequest.getLastName());

        teacherRepository.save(teacher);
        return mapper.map(teacher, TeacherResponse.class);
    }

    private Teacher getTeacherById(Long id) throws ResourceNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TEACHER, id)));
    }
}
