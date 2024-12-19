package com.abhishek.sms.payload.response.user;

import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.payload.response.abstracts.BaseUserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class TeacherResponse extends BaseUserResponse {

    private Date dateEmployed;

    private Subject subject;
}
