package com.abhishek.sms.entity.concretes.user;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.entity.concretes.academic.ExamResult;
import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.base.BasePersonEntity;
import com.abhishek.sms.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends BasePersonEntity implements Serializable {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean isWithdrawn;

    private boolean isSuspended;

    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.STUDENT;

    private boolean isPromotedToYear2;
    private boolean isPromotedToYear3;
    private boolean isGraduated;
    private String yearGraduated;
    private Date dateAdmitted;

    @ManyToOne
    @JoinColumn(name = "academicYear", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_academicYear_id"))
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
