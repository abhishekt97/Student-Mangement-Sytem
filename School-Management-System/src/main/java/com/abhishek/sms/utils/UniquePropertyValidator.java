package com.abhishek.sms.utils;

import com.abhishek.sms.exception.ConflictException;
import com.abhishek.sms.repository.user.UserRepository;
import com.abhishek.sms.utils.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePropertyValidator {

    private final UserRepository userRepository;


    public void checkDuplicate(String username, String email, String phoneNumber){

        if(userRepository.existsByEmail(email))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_EMAIL, email));

        if(userRepository.existsByPhoneNumber(phoneNumber))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_PHONE_NUMBER, phoneNumber));

        if(userRepository.existsByUsername(username))
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTERED_USERNAME, username));
    }
}
