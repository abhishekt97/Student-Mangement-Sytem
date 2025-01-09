package com.abhishek.sms.entity.concretes.user;

import com.abhishek.sms.entity.concretes.base.BasePersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parents")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parent extends BasePersonEntity implements Serializable {

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "parent")
    private Set<Student> students= new HashSet<>();

}
