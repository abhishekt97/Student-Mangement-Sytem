package com.abhishek.sms.payload.response.user;

import com.abhishek.sms.payload.response.abstracts.BaseUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
/*
When using @Builder on a class that extends another class Lombok does not take into account the fields of the superclasses but only the ones from the current class.
To make use of all fields from parent and child we use superBuilder.
never mix builder and superBuilder.
 */
public class UserResponse extends BaseUserResponse {

    private String phoneNumber;

    private LocalDate birthday;

    private String birthPlace;

}
