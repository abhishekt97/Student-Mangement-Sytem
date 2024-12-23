package com.abhishek.sms.service.user;

import com.abhishek.sms.entity.concretes.user.User;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface UserService {

    public UserResponse saveUser(UserRequest userRequest, String role);


    public Page<UserResponse> getUsersByPage(int page, int size, String sort, String type, String role);

    public UserResponse getUserById(Long id) throws ResourceNotFoundException;

    public UserResponse getUserByUsername(String username);

    public String  updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException;

    public String  deleteUserById(Long id, HttpServletRequest request) throws ResourceNotFoundException, BadRequestException;
}
