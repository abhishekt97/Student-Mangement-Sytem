package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.enums.Remark;
import com.abhishek.sms.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "exam_result")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResult extends BaseEntityWithIdLong implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "student_examResult_fk"))
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", foreignKey = @ForeignKey(name = "exam_examResult_fk"))
    private Exam exam;

    private int score;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Remark remark;

    private boolean isPublished;
}
