package com.abhishek.sms.service.user;

import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.TeacherRequest;
import com.abhishek.sms.payload.response.user.TeacherResponse;
import com.abhishek.sms.payload.response.user.UserResponse;

import java.util.List;

public interface TeacherService {

    public List<TeacherResponse> getAllTeachers();

    public Teacher saveTeacher(TeacherRequest teacherRequest);

    public String  deleteTeacherById(Long id) throws ResourceNotFoundException;

    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) throws ResourceNotFoundException;
}
