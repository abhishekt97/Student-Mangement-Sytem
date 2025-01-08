package com.abhishek.sms.payload.response.academic;

import com.abhishek.sms.entity.enums.Remark;
import com.abhishek.sms.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResultResponse {
    private String studentName;
    private String examName;
    private int score;
    private Status status;
    private Remark remark;
}
