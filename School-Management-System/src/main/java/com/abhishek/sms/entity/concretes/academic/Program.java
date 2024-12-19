package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program extends BaseEntityWithIdLong implements Serializable {


    private String name;
    private String description;
    private String duration;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();



}
