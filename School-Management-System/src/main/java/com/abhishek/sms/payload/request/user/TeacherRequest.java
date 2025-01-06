package com.abhishek.sms.payload.request.user;

import com.abhishek.sms.entity.concretes.academic.*;
import com.abhishek.sms.entity.enums.ApplicationStatus;
import com.abhishek.sms.entity.enums.RoleType;
import com.abhishek.sms.payload.request.abstracts.BaseUserRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class TeacherRequest extends BaseUserRequest {


    @NotNull(message = "Please enter your password!")
    @Size(min = 8, max = 60, message = "Your password should be at least 8 characters and maximum 60 characters.")
    private String password;

    @NotNull(message = "Please enter the date of employment!")
    private Date dateEmployed;

}
