package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ProgramRequest;
import com.abhishek.sms.payload.response.academic.ProgramResponse;
import org.springframework.data.domain.Page;

public interface ProgramService {
    Page<ProgramResponse> getAllPrograms(int page, int size);

    ProgramResponse getProgramByName(String name) throws ResourceNotFoundException;

    ProgramResponse getProgramForStudent(String username) throws ResourceNotFoundException;

    Program addProgram(ProgramRequest programRequest) throws ResourceNotFoundException;

    Program updateProgram(String name, ProgramRequest programRequest) throws ResourceNotFoundException;

    ProgramResponse addStudentToProgram(String name, Long id) throws ResourceNotFoundException;

    String  deleteProgram(String name) throws ResourceNotFoundException;
}
