package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.Program;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ProgramRequest;
import com.abhishek.sms.payload.response.academic.ProgramResponse;
import com.abhishek.sms.service.academic.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/all")
    public ResponseEntity<Page<ProgramResponse>> getAllPrograms(
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size", defaultValue = "10")int size
    ){
        return new ResponseEntity<>(programService.getAllPrograms(page, size), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProgramResponse> getProgramByName(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.getProgramByName(name), HttpStatus.OK);
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ProgramResponse> getProgramForStudent(@PathVariable Long studentId) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.getProgramForStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Program> addProgram(@Valid @RequestBody ProgramRequest programRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.addProgram(programRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Program> updateProgram(@Valid @RequestBody ProgramRequest programRequest,
                                              @PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.updateProgram(name, programRequest), HttpStatus.CREATED);
    }

    @PutMapping("/add/student/{programName}/{studentId}")
    public ResponseEntity<ProgramResponse> addStudentToProgram(@PathVariable(value = "programName") String name,
                                                       @PathVariable(value = "studentId") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.addStudentToProgram(name, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteProgram(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(programService.deleteProgram(name), HttpStatus.NO_CONTENT);
    }
}
