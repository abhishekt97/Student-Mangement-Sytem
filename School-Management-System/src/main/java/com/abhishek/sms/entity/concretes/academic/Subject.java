package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject extends BaseEntityWithIdLong implements Serializable {

    @Column(nullable = false)
    private String name;

    private String description;

    private String duration;

    @OneToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "teacher_subject_fk")
    )
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "academicYear_id", foreignKey = @ForeignKey(name = "subject_academicYear_fk"))
    private AcademicYear academicYear;

    @ManyToOne()
    @JoinColumn(name = "program_id", foreignKey = @ForeignKey(name = "subject_program_fk"))
    private Program program;

    @ManyToOne()
    @JoinColumn(name = "classLevel_id", foreignKey = @ForeignKey(name = "subject_classLevel_fk"))
    private ClassLevel classLevel;


}
