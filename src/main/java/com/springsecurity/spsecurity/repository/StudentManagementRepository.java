package com.springsecurity.spsecurity.repository;

import com.springsecurity.spsecurity.entity.StudentManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentManagementRepository extends JpaRepository<StudentManagementEntity, Long>
{

}
