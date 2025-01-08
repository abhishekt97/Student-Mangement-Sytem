package com.abhishek.sms.payload.request.academic;

import com.abhishek.sms.entity.concretes.academic.Exam;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.enums.Remark;
import com.abhishek.sms.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultRequest {

    private String  studentFirstName;

    private String  studentLastName;

    private String  examName;

    private int score;

    private Status status;

    private Remark remark;

    private Boolean isPublished;
}
