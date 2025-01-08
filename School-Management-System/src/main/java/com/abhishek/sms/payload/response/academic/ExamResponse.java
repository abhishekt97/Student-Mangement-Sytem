package com.abhishek.sms.payload.response.academic;

import com.abhishek.sms.entity.concretes.academic.*;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {

    private String name;
    private String description;
    private int totalMarks;
    private int passMarks;
    private String duration;
    private Date examDate;
    private String examType;
    private Subject subject;
    private Program program;
    private Set<Question> questions = new HashSet<>();
    private ClassLevel classLevel;
    private Teacher createdBy;
    private AcademicYear academicYear;

}
