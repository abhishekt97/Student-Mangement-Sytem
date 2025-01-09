package com.abhishek.sms.payload.response.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramResponse {

    private String name;
    private String description;
    private String duration;

    private Set<String> teachers;
    private List<String> students;
    private Set<String> subjects;

}
