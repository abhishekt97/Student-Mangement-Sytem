package com.abhishek.sms.utils.mapper;

import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.payload.response.academic.ProgramResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProgramMapper {

    public ProgramResponse mapProgramToProgramResponse(Program program){
        List<Student> studentList = program.getStudents();
        List<String> studentsNames = new ArrayList<>();
        for(Student s : studentList){
            studentsNames.add(s.getFirstName() + " " + s.getLastName());
        }
        Set<Teacher> teachers = program.getTeachers();
        Set<String> teachersNames = new HashSet<>();
        for (Teacher t : teachers){
            teachersNames.add(t.getFirstName() + " " + t.getLastName());
        }
        Set<Subject> subjects = program.getSubjects();
        Set<String > subjectsNames = new HashSet<>();
        for( Subject s : subjects){
            subjectsNames.add(s.getName());
        }

        return ProgramResponse.builder()
                .name(program.getName())
                .description(program.getDescription())
                .duration(program.getDuration())
                .students(studentsNames)
                .teachers(teachersNames)
                .subjects(subjectsNames)
                .build();
    }
}
