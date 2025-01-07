package com.abhishek.sms.payload.response.academic;

import com.abhishek.sms.entity.concretes.academic.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassLevelResponse {

    private String name;

    private String description;

    private Set<Subject> subjects;

}
