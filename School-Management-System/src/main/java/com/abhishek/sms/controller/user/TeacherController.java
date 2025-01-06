package com.abhishek.sms.controller.user;

import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.TeacherRequest;
import com.abhishek.sms.payload.response.user.TeacherResponse;
import com.abhishek.sms.payload.response.user.UserResponse;
import com.abhishek.sms.service.user.TeacherService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(teacherService.saveTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(teacherService.deleteTeacherById(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable Long id,
                                                         @Valid @RequestBody TeacherRequest teacherRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(teacherService.updateTeacher(id, teacherRequest), HttpStatus.CREATED);
    }
}
