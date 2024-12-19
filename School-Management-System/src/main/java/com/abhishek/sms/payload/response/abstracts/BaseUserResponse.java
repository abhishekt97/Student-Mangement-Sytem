package com.abhishek.sms.payload.response.abstracts;


import com.abhishek.sms.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUserResponse {


    private String username;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private Gender gender;

    private String role;
}
