package com.abhishek.sms.service.user;

import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
public interface UserService {

    public UserResponse saveUser(UserRequest userRequest, String role);

    public UserResponse getUserById(Long id) throws ResourceNotFoundException;

    public UserResponse getUserByUsername(String username);

    public String  updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException;

    public String  deleteUserById(Long id, HttpServletRequest request) throws ResourceNotFoundException, BadRequestException;
}
