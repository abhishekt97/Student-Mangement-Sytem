package com.abhishek.sms.repository.academic;

import com.abhishek.sms.entity.concretes.academic.YearGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearGroupRepository extends JpaRepository<YearGroup, Long> {
}
