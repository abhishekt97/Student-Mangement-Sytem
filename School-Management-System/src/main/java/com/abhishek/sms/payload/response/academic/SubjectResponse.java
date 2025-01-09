package com.abhishek.sms.payload.response.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponse {

    private String name;

    private String description;

    private String duration;

    private String teacherName;

}
