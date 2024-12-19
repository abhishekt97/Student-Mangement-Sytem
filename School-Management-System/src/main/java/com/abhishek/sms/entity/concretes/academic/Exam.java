package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam extends BaseEntityWithIdLong {

    private String name;
    private String description;
    @Column(nullable = false, columnDefinition = "int default 100")
    private int totalMarks;
    @Column(nullable = false, columnDefinition = "int default 35")
    private int passMarks;
    @Column(nullable = false, columnDefinition = "varchar(255) default '120 minute'")
    private String duration;
    private Date examDate;
    private String examType;
    private Subject subject;
    private Program program;
    @OneToMany()
    private Set<Question> questions = new HashSet<>();
    @OneToOne()
    private ClassLevel classLevel;
    @ManyToOne()
    @JoinColumn(
            name = "teacher_id",
            foreignKey =@ForeignKey(name = "teacher_exam_fk")
    )
    private Teacher createdBy;

    @OneToOne()
    private AcademicTerm academicTerm;

    @OneToOne()
    private AcademicYear academicYear;

    @OneToOne(mappedBy = "exam")
    private ExamResult result;
}
