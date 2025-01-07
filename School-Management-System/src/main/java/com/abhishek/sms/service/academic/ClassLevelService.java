package com.abhishek.sms.service.academic;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ClassLevelRequest;
import com.abhishek.sms.payload.response.academic.ClassLevelResponse;
import org.springframework.data.domain.Page;

public interface ClassLevelService {

    Page<ClassLevelResponse> getAllClassLevels(int page, int size);

    ClassLevelResponse getClassByName(String name) throws ResourceNotFoundException;

    ClassLevel addClassLevel(ClassLevelRequest classLevelRequest);

    ClassLevel updateClass(String name, ClassLevelRequest classLevelRequest) throws ResourceNotFoundException;

    String  deleteClassLevel(String name) throws ResourceNotFoundException;
}
