package com.abhishek.sms.payload.response.user;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.user.Parent;
import com.abhishek.sms.payload.response.abstracts.BaseUserResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class StudentResponse extends BaseUserResponse {


    private boolean isSuspended;
    private AcademicYear academicYear;

    @ManyToOne()
    @JoinColumn(name = "classLevel", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_classLevel_fk"))
    private ClassLevel classLevel;

    @OneToOne(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    private ExamResult examResult;

    @ManyToOne()
    @JoinColumn(name = "program_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_program_fk"))
    private Program program;

    @ManyToOne()
    @JoinColumn(name = "parent_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_parent_fk"))
    private Parent parent;
}
