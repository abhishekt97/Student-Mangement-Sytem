package com.abhishek.sms.service.user.impl;

import com.abhishek.sms.entity.concretes.user.User;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import com.abhishek.sms.repository.user.UserRepository;
import com.abhishek.sms.service.user.UserService;
import com.abhishek.sms.utils.ErrorMessages;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.UniquePropertyValidator;
import com.abhishek.sms.utils.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UniquePropertyValidator validator;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PageUtil pageUtil;

    @Override
    public UserResponse saveUser(UserRequest userRequest, String role) {

        validator.checkDuplicate(userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPhoneNumber());

        User user = mapper.mapUserRequestToUser(userRequest);
        User save = userRepository.save(user);

        return mapper.mapUserToUserResponse(save);
    }

    @Override
    public Page<UserResponse> getUsersByPage(int page, int size, String sort, String type, String role) {

        Pageable pageable = pageUtil.getPageableWithProperties(page, size, sort, type);

        Page<User> userByRole = userRepository.findByUserByRole(role, pageable);

        return userByRole.map(mapper::mapUserToUserResponse);
    }

    @Override
    public UserResponse getUserById(Long id) throws ResourceNotFoundException {

        User user= userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER, id)));

        return mapper.mapUserToUserResponse(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {

        User byUsername = userRepository.findByUsername(username);

        return mapper.mapUserToUserResponse(byUsername);

    }

    @Override
    public String updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER, id)));



    }


}
