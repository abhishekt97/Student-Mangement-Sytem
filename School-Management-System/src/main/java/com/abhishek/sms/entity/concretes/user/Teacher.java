package com.abhishek.sms.entity.concretes.user;

import com.abhishek.sms.entity.concretes.academic.*;
import com.abhishek.sms.entity.concretes.base.BasePersonEntity;
import com.abhishek.sms.entity.enums.ApplicationStatus;
import com.abhishek.sms.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends BasePersonEntity implements Serializable {

    private String username;

    private String password;

    private Date dateEmployed;

    @Column(columnDefinition = "boolean default false")
    private boolean isSuspended;

    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.TEACHER;

    @OneToOne(
            mappedBy = "teacher",
            cascade = CascadeType.PERSIST
    )
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "program_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "teacher_program_fk"))
    private Program program;

    @ManyToOne()
    @JoinColumn(name = "classLevel_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "teacher_classLevel_fk")
    )
    private ClassLevel classLevel;

    @ManyToOne()
    @JoinColumn(
            name = "academicYear_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "teacher_academicYear_fk")
    )
    private AcademicYear academicYear;

    @OneToMany(
            mappedBy = "createdBy",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Exam> examsCreated = new HashSet<>();

}
