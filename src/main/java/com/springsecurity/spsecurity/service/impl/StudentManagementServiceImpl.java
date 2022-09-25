package com.springsecurity.spsecurity.service.impl;

import com.springsecurity.spsecurity.entity.StudentManagementEntity;
import com.springsecurity.spsecurity.repository.StudentManagementRepository;
import com.springsecurity.spsecurity.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentManagementServiceImpl implements StudentManagementService
{
    private StudentManagementRepository studentManagementRepository;

    @Autowired
    private StudentManagementServiceImpl(StudentManagementRepository studentManagementRepository)
                                        { this.studentManagementRepository = studentManagementRepository ; }


    @Override
    public List<StudentManagementEntity> getAllStudents() {
        return studentManagementRepository.findAll();
    }

    @Override
    public StudentManagementEntity saveStudent(StudentManagementEntity studentManagementEntity) {
        return studentManagementRepository.save(studentManagementEntity);
    }

    @Override
    public StudentManagementEntity getStudentByID(Long id) {
        return studentManagementRepository.findById(id).get();
    }

    @Override
    public StudentManagementEntity updateStudent(StudentManagementEntity studentManagementEntity) {
        return studentManagementRepository.save(studentManagementEntity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentManagementRepository.deleteById(id);
    }
}
