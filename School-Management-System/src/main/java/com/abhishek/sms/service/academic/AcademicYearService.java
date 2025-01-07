package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface AcademicYearService {


    Page<AcademicYear> getAllYears(int page, int size);

    AcademicYear getAcademicYearByName(String name) throws ResourceNotFoundException;

    AcademicYear updateAcademicYear(AcademicYear academicYearInfo) throws ResourceNotFoundException;

    String  deleteAcademicYear(Long id) throws ResourceNotFoundException;
}
