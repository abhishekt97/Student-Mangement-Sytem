package com.abhishek.sms.payload.request.academic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequest {

    private String name;
    private String description;
    private String duration;

    private Set<Long> teachersId;
    private List<Long> studentsId;
    private Set<String> subjectsName;

}
