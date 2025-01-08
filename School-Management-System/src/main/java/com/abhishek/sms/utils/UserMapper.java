package com.abhishek.sms.utils;

import com.abhishek.sms.entity.concretes.user.User;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRequestToUser(UserRequest userRequest){

        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .gender(userRequest.getGender())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .birthday(userRequest.getBirthDay())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .fatherName(userRequest.getFatherName())
                .motherName(userRequest.getMotherName())
                .build();
    }

    public UserResponse mapUserToUserResponse(User user){

        return UserResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .birthPlace(user.getBirthPlace())
                .email(user.getEmail())
                .role(user.getRoleType().roleText)
                .build();
    }

}
