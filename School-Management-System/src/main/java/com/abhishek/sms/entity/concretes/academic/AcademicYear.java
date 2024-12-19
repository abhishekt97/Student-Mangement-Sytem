package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicYear extends BaseEntityWithIdLong implements Serializable {

    private String name;
    private Date fromYear;
    private Date toYear;
    private boolean isActive;

    @OneToMany(
            mappedBy = "academicYear",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Student> students = new ArrayList<>();
    @OneToMany(
            mappedBy = "academicYear",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();
}
