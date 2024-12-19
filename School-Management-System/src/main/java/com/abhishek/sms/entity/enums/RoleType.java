package com.abhishek.sms.entity.enums;

import lombok.Getter;

@Getter
public enum RoleType {

    ADMIN("Admin"),
    TEACHER("Teacher"),
    STUDENT("Student"),
    PRINCIPLE("Principle"),
    VICE_PRINCIPLE("Vice_Principle"),
    HELPER("Helper");

    public final String roleText;

    RoleType(String roleText){
        this.roleText= roleText;
    }

}
