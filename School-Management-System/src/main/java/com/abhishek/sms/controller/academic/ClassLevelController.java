package com.abhishek.sms.controller.academic;

import com.abhishek.sms.entity.concretes.academic.ClassLevel;
import com.abhishek.sms.exception.ResourceNotFoundException;
import com.abhishek.sms.payload.request.academic.ClassLevelRequest;
import com.abhishek.sms.payload.response.academic.ClassLevelResponse;
import com.abhishek.sms.service.academic.ClassLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/class")
@RequiredArgsConstructor
@RestController
public class ClassLevelController {

    private final ClassLevelService classLevelService;

    @GetMapping("/all")
    public ResponseEntity<Page<ClassLevelResponse>> getAllClassLevels(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return new ResponseEntity<>(classLevelService.getAllClassLevels(page, size), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClassLevelResponse> getClassByName(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(classLevelService.getClassByName(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ClassLevel> addClassLevel(@RequestBody ClassLevelRequest classLevelRequest){
        return new ResponseEntity<>(classLevelService.addClassLevel(classLevelRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<ClassLevel> updateClass(@PathVariable String  name,
                                                  @RequestBody ClassLevelRequest classLevelRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(classLevelService.updateClass(name, classLevelRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteClassLevel(@PathVariable String name) throws ResourceNotFoundException {
        return new ResponseEntity<>(classLevelService.deleteClassLevel(name), HttpStatus.NO_CONTENT);
    }
}
