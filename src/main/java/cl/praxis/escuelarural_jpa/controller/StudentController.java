package cl.praxis.escuelarural_jpa.controller;

import cl.praxis.escuelarural_jpa.entity.Student;
import cl.praxis.escuelarural_jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    @PostMapping("")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student_edit_form";
    }

    @PutMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student) {
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}

