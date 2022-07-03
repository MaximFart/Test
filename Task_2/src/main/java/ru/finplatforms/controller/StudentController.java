package ru.finplatforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.finplatforms.controller.exception.NotFoundException;
import ru.finplatforms.model.Student;
import ru.finplatforms.service.StudentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/students";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("student", studentService.findById(id).orElseThrow(NotFoundException::new));
        return "student/show";
    }

    @GetMapping("/new")
    public String newCourse(@ModelAttribute("student") Student student) {
        return "student/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) throws Exception {
        model.addAttribute("student", studentService.findById(id).orElseThrow(NotFoundException::new));
        return "student/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") Student student) {
        studentService.update(student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)  {
        studentService.delete(id);
        return "redirect:/students";
    }
}
