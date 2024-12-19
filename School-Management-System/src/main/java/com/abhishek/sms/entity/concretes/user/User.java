package com.abhishek.sms.entity.concretes.user;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import com.abhishek.sms.entity.enums.Gender;
import com.abhishek.sms.entity.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "_user")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntityWithIdLong {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(updatable = false, nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate birthday;

    private String birthPlace;

    @Column(name = "father_name")
    private String fatherName;

    private String motherName;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
