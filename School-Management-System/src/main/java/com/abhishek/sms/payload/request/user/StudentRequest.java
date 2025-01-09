package com.abhishek.sms.payload.request.user;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.user.Parent;
import com.abhishek.sms.payload.request.abstracts.BaseUserRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class StudentRequest extends BaseUserRequest {

    private boolean isSuspended;

    private AcademicYear academicYear;

    private ClassLevel classLevel;

    private ExamResult examResult;

    private Program program;

    private Parent parent;
}
