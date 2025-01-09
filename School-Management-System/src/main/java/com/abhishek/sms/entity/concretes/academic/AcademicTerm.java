package com.abhishek.sms.entity.concretes.academic;

import com.abhishek.sms.entity.concretes.base.BaseEntityWithIdLong;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicTerm extends BaseEntityWithIdLong implements Serializable {

    private String name;
    private String description;
    private String duration;
}
