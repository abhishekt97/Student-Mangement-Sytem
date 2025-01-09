package com.abhishek.sms.repository.user;

import com.abhishek.sms.entity.concretes.user.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {


}
