package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.AcademicYear;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.repository.academic.AcademicYearRepository;
import com.abhishek.sms.service.academic.AcademicYearService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {

    private final AcademicYearRepository academicYearRepository;
    private final PageUtil pageUtil;

    @Override
    public Page<AcademicYear> getAllYears(int page, int size) {

        Pageable pageable = pageUtil.getPageableWithProperties(page, size);
        return academicYearRepository.findAll(pageable);
    }

    @Override
    public AcademicYear getAcademicYearByName(String name) throws ResourceNotFoundException {

        return academicYearRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ACADEMIC_YEAR, name)));
    }

    @Override
    public AcademicYear updateAcademicYear(AcademicYear academicYearInfo) throws ResourceNotFoundException {

        AcademicYear academicYear = getAcademicYearById(academicYearInfo.getId());

        academicYear.setFromYear(academicYearInfo.getFromYear());
        academicYear.setToYear(academicYearInfo.getToYear());
        academicYear.setName(academicYearInfo.getName());
        academicYear.setIsActive(academicYearInfo.getIsActive());
        academicYear.setStudents(academicYearInfo.getStudents());
        academicYear.setTeachers(academicYearInfo.getTeachers());

        return academicYearRepository.save(academicYear);
    }


    @Override
    public String deleteAcademicYear(Long id) throws ResourceNotFoundException {
        AcademicYear academicYearById = getAcademicYearById(id);
        academicYearRepository.delete(academicYearById);
        return SuccessMessages.ACADEMIC_YEAR_DELETED;
    }


    private AcademicYear getAcademicYearById(Long id) throws ResourceNotFoundException {
        return academicYearRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ACADEMIC_YEAR_ID, id))
        );
    }

}
