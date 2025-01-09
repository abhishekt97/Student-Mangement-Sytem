package com.abhishek.sms.payload.request.academic;

import com.abhishek.sms.entity.concretes.academic.AcademicTerm;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest {

    @NotNull(message = "Please enter the name")
    @Min(value = 2, message = "name should be at least 2 letters long!")
    private String name;

    @NotNull(message = "Please enter the description")
    @Size(min = 2,max = 50, message = "Description should be 2-50 words long.")
    private String description;

    @NotNull(message = "Please enter the duration")
    private String duration;

    @NotNull(message = "Please enter the teacher's firstname")
    private String teacherFirstName;

    @NotNull(message = "Please enter the teacher's lastname")
    private String teacherLastName;

    private String classLevel;

    private String academicYear;

}
