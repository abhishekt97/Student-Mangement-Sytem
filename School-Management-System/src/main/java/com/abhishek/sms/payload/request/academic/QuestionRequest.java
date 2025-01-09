package com.abhishek.sms.payload.request.academic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String problem;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private Boolean isCorrect;
    private String subjectName;
    private String createdByTeacherFirstName;
    private String createdByTeacherLastName;
}
