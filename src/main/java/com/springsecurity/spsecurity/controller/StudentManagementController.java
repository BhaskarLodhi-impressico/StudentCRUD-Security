package com.springsecurity.spsecurity.controller;

import com.springsecurity.spsecurity.dto.UserRegistrationDto;
import com.springsecurity.spsecurity.entity.StudentManagementEntity;
import com.springsecurity.spsecurity.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentManagementController
{

    private final StudentManagementService studentManagementService;

    @Autowired
    private StudentManagementController(StudentManagementService studentManagementService)
                                        { this.studentManagementService = studentManagementService ; }

    @GetMapping("/students")
    public String listOfStudents(Model model)
    {
        model.addAttribute("students", studentManagementService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudent(Model model)
    {
        StudentManagementEntity studentManagementEntity = new StudentManagementEntity();

        model.addAttribute("student", studentManagementEntity);

        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") StudentManagementEntity studentManagementEntity)
    {
        studentManagementService.saveStudent(studentManagementEntity);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model)
    {
        model.addAttribute("student", studentManagementService.getStudentByID(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute("student") UserRegistrationDto userRegistrationDto,
                                Model model)
    {
        StudentManagementEntity existingStudent = studentManagementService.getStudentByID(id);

        existingStudent.setId(id);
        existingStudent.setFirstName(userRegistrationDto.getFirstName());
        existingStudent.setLastName(userRegistrationDto.getLastName());
        existingStudent.setEmail(userRegistrationDto.getEmail());

        studentManagementService.updateStudent(existingStudent);

        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id)
    {
        studentManagementService.deleteStudent(id);
        return "redirect:/students";
    }
}
