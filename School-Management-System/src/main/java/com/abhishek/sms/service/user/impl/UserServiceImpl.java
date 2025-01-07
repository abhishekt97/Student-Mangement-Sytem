package com.abhishek.sms.service.user.impl;

import com.abhishek.sms.entity.concretes.user.User;
import com.abhishek.sms.entity.enums.RoleType;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import com.abhishek.sms.repository.user.UserRepository;
import com.abhishek.sms.service.user.UserService;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.UserMapper;
import com.abhishek.sms.utils.messages.SuccessMessages;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final Validator validator;
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

        User user= findUserById(id);

        return mapper.mapUserToUserResponse(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {

        User byUsername = userRepository.findByUsername(username);

        return mapper.mapUserToUserResponse(byUsername);

    }

    @Override
    public String updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException {

        User user = findUserById(id);

        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setBirthday(userRequest.getBirthDay());
        user.setGender(userRequest.getGender());
        user.setFatherName(userRequest.getFatherName());
        user.setMotherName(userRequest.getMotherName());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        return SuccessMessages.USER_UPDATE;
    }

    @Override
    public String deleteUserById(Long id, HttpServletRequest request) throws ResourceNotFoundException, BadRequestException {
        User user = findUserById(id);

        String username = (String) request.getAttribute("username");
        User loggedInUser = userRepository.findByUsername(username);

        RoleType loggedInUserRole = loggedInUser.getRoleType();
        RoleType deletedUserRole = user.getRoleType();

        if(loggedInUserRole == RoleType.STUDENT ||
           loggedInUserRole == RoleType.HELPER ||
           loggedInUserRole == RoleType.TEACHER){
            if (loggedInUserRole != deletedUserRole){
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD);
            }
        }else if ((loggedInUserRole == RoleType.VICE_PRINCIPLE) &&
            (deletedUserRole == RoleType.PRINCIPLE || deletedUserRole == RoleType.ADMIN)){
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD);
        }
        userRepository.deleteById(id);
        return SuccessMessages.USER_DELETE;
    }

    private User findUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER, id)));
    }
}
