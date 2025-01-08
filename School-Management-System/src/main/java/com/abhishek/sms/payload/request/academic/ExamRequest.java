package com.abhishek.sms.payload.request.academic;

import com.abhishek.sms.entity.concretes.academic.*;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequest {

    @NotNull(message = "Please enter exam name.")
    @Min(value = 2, message = "Name must be at least 2 character long!")
    private String name;

    @NotNull(message = "Please enter description")
    @Size(min = 3, max = 50, message = "Description should be between 3-50 words!")
    private String description;

    @NotNull(message = "Please enter total number of marks.")
    private int totalMarks;

    @NotNull(message = "Please specify the pass marks.")
    private int passMarks;

    @NotNull(message = "Please enter exam duration.")
    private String duration;

    @NotNull(message = "Please enter exam date.")
    private Date examDate;

    @NotNull(message = "Please enter exam type.")
    private String examType;

    @NotNull(message = "Please enter the subject.")
    private Subject subject;

    @NotNull(message = "Please give questions.")
    private Set<Question> questions;

    @NotNull(message = "Please specify class level")
    private ClassLevel classLevel;

    @NotNull(message = "Please enter exam creator's name.")
    private Teacher createdBy;

    @NotNull(message = "Please enter academic year.")
    private AcademicYear academicYear;
}

