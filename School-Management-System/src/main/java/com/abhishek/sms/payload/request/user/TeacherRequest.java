package com.abhishek.sms.payload.request.user;

import com.abhishek.sms.payload.request.abstracts.BaseUserRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public class TeacherRequest extends BaseUserRequest {


    @NotNull(message = "Please enter your password!")
    @Size(min = 8, max = 60, message = "Your password should be at least 8 characters and maximum 60 characters.")
    private String password;

    @NotNull(message = "Please enter the date of employment!")
    private Date dateEmployed;

}
