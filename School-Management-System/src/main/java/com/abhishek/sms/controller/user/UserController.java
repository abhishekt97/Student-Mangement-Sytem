package com.abhishek.sms.controller.user;

import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.user.UserRequest;
import com.abhishek.sms.payload.response.user.UserResponse;
import com.abhishek.sms.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/save/{role}")
    public ResponseEntity<UserResponse> saveUser(
            @PathVariable String role,
            @RequestBody @Valid UserRequest userRequest
            ){
        return new ResponseEntity<>(userService.saveUser(userRequest, role), HttpStatus.CREATED);
    }


    @GetMapping("/getAllUsersByPage/{role}")
    public ResponseEntity<Page<UserResponse>> getUsersByPage(
            @PathVariable String role,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "name") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return new ResponseEntity<>(userService.getUsersByPage(page, size, sort, type, role), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id
    ) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<UserResponse> getUserByUsername(
            @PathVariable String name
    ){
        return new ResponseEntity<>(userService.getUserByUsername(name), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequest userRequest
    ) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws ResourceNotFoundException, BadRequestException {
        return new ResponseEntity<>(userService.deleteUserById(id, request), HttpStatus.NO_CONTENT);
    }


}
