package com.abhishek.sms.payload.request.abstracts;

import com.abhishek.sms.entity.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseUserRequest {


    @NotNull(message = "Please enter username.")
    @Size(min = 4, max = 16, message = "Your username should be between 4-16 characters.")
    private String username;


    @NotNull(message = "Please enter firstName.")
    @Size(min = 2, max = 18, message = "Your username should be between 4-16 characters.")
    private String firstName;

    @NotNull(message = "Please enter lastName.")
    @Size(min = 2, max = 18, message = "Your username should be between 4-16 characters.")
    private String lastName;

    @NotNull(message = "Please enter age.")
    private int age;

    @NotNull(message = "Please enter email.")
    @Email
    @Size(min = 4, max = 50, message = "Your username should be between 4-50 characters.")
    private String email;

    @NotNull(message = "Please enter your gender.")
    private Gender gender;

    @NotNull(message = "Please enter your phone number")
    private String phoneNumber;

}
