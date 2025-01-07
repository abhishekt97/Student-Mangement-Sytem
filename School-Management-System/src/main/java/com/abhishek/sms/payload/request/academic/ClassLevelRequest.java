package com.abhishek.sms.payload.request.academic;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassLevelRequest {

    @NotNull(message = "Please enter the name of class.")
    @Min(value = 2, message = "Name should be at least 2 word long.")
    private String name;

    @NotNull(message = "Please enter the description of the class")
    @Size(min = 2,max = 50, message = "Description should be between 2-50 words long")
    private String description;

}
