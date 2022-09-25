package com.springsecurity.spsecurity.service;

import com.springsecurity.spsecurity.entity.StudentManagementEntity;

import java.util.List;

public interface StudentManagementService
{
    List<StudentManagementEntity> getAllStudents();

    StudentManagementEntity saveStudent(StudentManagementEntity studentManagementEntity);

    StudentManagementEntity getStudentByID(Long id);

    StudentManagementEntity updateStudent(StudentManagementEntity studentManagementEntity);

    void deleteStudent(Long id);
}
