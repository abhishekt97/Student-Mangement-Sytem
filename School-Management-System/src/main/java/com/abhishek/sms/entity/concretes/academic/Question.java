package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.concretes.user.Teacher;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Question extends BaseEntityWithIdLong {

    private String problem;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private boolean isCorrect;

    @ManyToOne()
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(name = "subject_question_fk")
    )
    private Subject subject;


    @ManyToOne()
    @JoinColumn(
            name = "teacher_id",
            foreignKey =@ForeignKey(name = "teacher_question_fk")
    )
    private Teacher createdBy;

}
