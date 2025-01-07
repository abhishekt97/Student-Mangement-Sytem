package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.service.academic.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/years")
public class AcademicYearController {

    private AcademicYearService academicYearService;

    @GetMapping("/all")
    public ResponseEntity<Page<AcademicYear>> getAllYears(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        return new ResponseEntity<>(academicYearService.getAllYears(page, size), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<AcademicYear> getAcademicYearByName(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(academicYearService.getAcademicYearByName(name), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AcademicYear> updateAcademicYear(@RequestBody AcademicYear academicYearInfo) throws ResourceNotFoundException {
        return new ResponseEntity<>(academicYearService.updateAcademicYear(academicYearInfo), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAcademicYear(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(academicYearService.deleteAcademicYear(id), HttpStatus.NO_CONTENT);
    }
}
