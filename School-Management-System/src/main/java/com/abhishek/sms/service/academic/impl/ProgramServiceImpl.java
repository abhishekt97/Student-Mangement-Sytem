package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.entity.concretes.academic.Subject;
import com.abhishek.sms.entity.concretes.user.Student;
import com.abhishek.sms.entity.concretes.user.Teacher;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ProgramRequest;
import com.abhishek.sms.payload.response.academic.ProgramResponse;
import com.abhishek.sms.repository.academic.ProgramRepository;
import com.abhishek.sms.repository.academic.SubjectRepository;
import com.abhishek.sms.repository.user.StudentRepository;
import com.abhishek.sms.repository.user.TeacherRepository;
import com.abhishek.sms.service.academic.ProgramService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.mapper.ProgramMapper;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final PageUtil pageUtil;
    private final Validator validator;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public Page<ProgramResponse> getAllPrograms(int page, int size) {
        List<Program> programs = programRepository.findAll();
        List<ProgramResponse> programResponseList= new ArrayList<>();
        for (Program p : programs){
            programResponseList.add(mapper.mapProgramToProgramResponse(p));
        }

        Pageable pageable = pageUtil.getPageableWithProperties(page, size);
        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), programResponseList.size());
        List<ProgramResponse> pageProgramResponseList = programResponseList.subList(start, end);

        return new PageImpl<>(pageProgramResponseList, pageable, programResponseList.size());
    }

    @Override
    public ProgramResponse getProgramByName(String name) throws ResourceNotFoundException {

        Program program = getProgram(name);
        return mapper.mapProgramToProgramResponse(program);
    }


    @Override
    public ProgramResponse getProgramForStudent(String username) throws ResourceNotFoundException {

        Program program = programRepository.findByStudentsUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_PROGRAM_FOR_STUDENT_ID, username)));

        return mapper.mapProgramToProgramResponse(program);
    }

    @Override
    public Program addProgram(ProgramRequest programRequest) throws ResourceNotFoundException {
        validator.checkDuplicateProgram(programRequest.getName());

        List<Student> studentList = getStudentList(programRequest);
        Set<Teacher> teacherSet = getTeacherSet(programRequest);
        Set<Subject> subjectSet = getSubjectSet(programRequest);

        Program newProgram = Program.builder()
                .name(programRequest.getName())
                .description(programRequest.getDescription())
                .duration(programRequest.getDuration())
                .students(studentList)
                .teachers(teacherSet)
                .subjects(subjectSet)
                .build();

        return programRepository.save(newProgram);
    }

    @Override
    public Program updateProgram(String name, ProgramRequest programRequest) throws ResourceNotFoundException {
        Program program = getProgram(name);

        program.setName(programRequest.getName());
        program.setDescription(programRequest.getDescription());
        program.setDuration(programRequest.getDuration());
        program.setStudents(getStudentList(programRequest));
        program.setTeachers(getTeacherSet(programRequest));
        program.setSubjects(getSubjectSet(programRequest));

        return programRepository.save(program);
    }

    @Override
    public ProgramResponse addStudentToProgram(String name, Long id) throws ResourceNotFoundException {
        Student student = getStudent(id);
        Program program = getProgram(name);

        program.getStudents().add(student);
        return mapper.mapProgramToProgramResponse(programRepository.save(program));
    }

    @Override
    public String deleteProgram(String name) throws ResourceNotFoundException {
        Program program = getProgram(name);
        programRepository.delete(program);
        return SuccessMessages.PROGRAM_DELETED;
    }

    private List<Student> getStudentList(ProgramRequest programRequest) throws ResourceNotFoundException {
        List<Long> studentsId = programRequest.getStudentsId();
        List<Student> studentList = new ArrayList<>();
        for (Long id : studentsId){
            studentList.add(getStudent(id));
        }
        return studentList;
    }

    private Set<Teacher> getTeacherSet(ProgramRequest programRequest) throws ResourceNotFoundException {
        Set<Long> teachersId = programRequest.getTeachersId();
        Set<Teacher> teacherSet = new HashSet<>();
        for( Long id : teachersId){
            teacherSet.add(getTeacher(id));
        }
        return teacherSet;
    }

    private Set<Subject> getSubjectSet(ProgramRequest programRequest) throws ResourceNotFoundException {
        Set<String> subjectsName = programRequest.getSubjectsName();
        Set<Subject> subjectSet = new HashSet<>();
        for( String name : subjectsName){
            subjectSet.add(getSubject(name));
        }
        return subjectSet;
    }

    private Program getProgram(String name) throws ResourceNotFoundException {
        return programRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_PROGRAM, name)));
    }

    private Subject getSubject(String name) throws ResourceNotFoundException {
        return subjectRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_SUBJECT, name)));
    }

    private Teacher getTeacher(Long id) throws ResourceNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TEACHER, id)));

    }
    private Student getStudent(Long id) throws ResourceNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_STUDENT_MESSAGE, id)));
    }

}
