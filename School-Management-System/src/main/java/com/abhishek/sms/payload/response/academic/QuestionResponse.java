package com.abhishek.sms.payload.response.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private String problem;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String  subjectName;
    private String createdByTeacher;

}
