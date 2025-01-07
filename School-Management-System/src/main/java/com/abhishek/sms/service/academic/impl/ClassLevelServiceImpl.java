package com.abhishek.sms.service.academic.impl;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ClassLevelRequest;
import com.abhishek.sms.payload.response.academic.ClassLevelResponse;
import com.abhishek.sms.repository.academic.ClassLevelRepository;
import com.abhishek.sms.service.academic.ClassLevelService;
import com.abhishek.sms.utils.PageUtil;
import com.abhishek.sms.utils.Validator;
import com.abhishek.sms.utils.messages.ErrorMessages;
import com.abhishek.sms.utils.messages.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassLevelServiceImpl implements ClassLevelService {

    private final ClassLevelRepository classLevelRepository;
    private final PageUtil pageUtil;
    private final ModelMapper mapper;
    private final Validator validator;


    @Override
    public Page<ClassLevelResponse> getAllClassLevels(int page,int size) {

        Pageable pageable = pageUtil.getPageableWithProperties(page, size);

        List<ClassLevel> classLevels = classLevelRepository.findAll();
        List<ClassLevelResponse> classLevelResponses = new ArrayList<>();
        for(ClassLevel c : classLevels){
            classLevelResponses.add(mapper.map(c, ClassLevelResponse.class));
        }
        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), classLevels.size());
        //sublist will form the content of our Page object:
        List<ClassLevelResponse> pageContent = classLevelResponses.subList(start, end);

        //it will encapsulate pageContent as well as the pageRequest along with the total size
        return new PageImpl<>(pageContent, pageable, classLevels.size());
    }

    @Override
    public ClassLevelResponse getClassByName(String name) throws ResourceNotFoundException {
        ClassLevel classLevel = getClassLevelByName(name);
        return mapper.map(classLevel, ClassLevelResponse.class);
    }

    @Override
    public ClassLevel addClassLevel(ClassLevelRequest classLevelRequest) {
        validator.checkDuplicateClassLevel(classLevelRequest.getName());
        return classLevelRepository.save(mapper.map(classLevelRequest, ClassLevel.class));
    }

    @Override
    public ClassLevel updateClass(String name, ClassLevelRequest classLevelRequest) throws ResourceNotFoundException {
        ClassLevel classLevel = getClassLevelByName(name);

        classLevel.setName(classLevelRequest.getName());
        classLevel.setDescription(classLevelRequest.getDescription());

        return classLevelRepository.save(classLevel);
    }

    @Override
    public String deleteClassLevel(String name) throws ResourceNotFoundException {

        ClassLevel classLevel = getClassLevelByName(name);
        classLevelRepository.delete(classLevel);
        return SuccessMessages.CLASS_LEVEL_DELETED;
    }

    private ClassLevel getClassLevelByName(String name) throws ResourceNotFoundException {
        return classLevelRepository.findByName(name).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CLASS_LEVEL, name))
        );
    }
}
