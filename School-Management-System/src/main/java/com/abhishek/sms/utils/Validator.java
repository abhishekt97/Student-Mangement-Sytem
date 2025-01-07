package com.abhishek.sms.utils;

import com.abhishek.sms.exception.ConflictException;
import com.abhishek.sms.repository.academic.ClassLevelRepository;
import com.abhishek.sms.repository.user.StudentRepository;
import com.abhishek.sms.repository.user.TeacherRepository;
import com.abhishek.sms.repository.user.UserRepository;
import com.abhishek.sms.utils.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ClassLevelRepository classLevelRepository;

    public void checkDuplicate(String username, String email, String phoneNumber){

        if(userRepository.existsByEmail(email))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_EMAIL, email));

        if(userRepository.existsByPhoneNumber(phoneNumber))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_PHONE_NUMBER, phoneNumber));

        if(userRepository.existsByUsername(username))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_USERNAME, username));
    }

    public void checkDuplicateStudent(String username, String email, String phoneNumber){
        if(studentRepository.existsByEmail(email))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_EMAIL, email));

        if(studentRepository.existsByPhoneNumber(phoneNumber))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_PHONE_NUMBER, phoneNumber));

        if(studentRepository.existsByUsername(username))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_USERNAME, username));
    }

    public void checkDuplicateTeacher(String username, String email, String phoneNumber){
        if(teacherRepository.existsByEmail(email))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_EMAIL, email));

        if(teacherRepository.existsByPhoneNumber(phoneNumber))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_PHONE_NUMBER, phoneNumber));

        if(teacherRepository.existsByUsername(username))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_USERNAME, username));
    }

    public void checkDuplicateClassLevel(String name){
        if(classLevelRepository.existsByName(name))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_NAME, name));
    }


}
