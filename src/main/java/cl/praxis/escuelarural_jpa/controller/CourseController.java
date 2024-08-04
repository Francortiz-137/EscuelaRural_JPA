package cl.praxis.escuelarural_jpa.controller;

import cl.praxis.escuelarural_jpa.entity.Course;
import cl.praxis.escuelarural_jpa.entity.Student;
import cl.praxis.escuelarural_jpa.service.ICourseService;
import cl.praxis.escuelarural_jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private ICourseService courseService;
    private IStudentService studentService;

    @Autowired
    public CourseController(ICourseService courseService, IStudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/{id}")
    public String getCourse(ModelMap modelMap, @PathVariable Long id) {
        Course course = courseService.getCourse(id);
        List<Student> students = studentService.getAllStudents();
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", students);
        return "course";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @PostMapping("")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
        return "course_edit_form";
    }

    @PutMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("course") Course course) {
        course.setId(id);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("{id}/addStudent")
    public String addStudent(@PathVariable Long id,
                             @RequestParam("studentId") Long studentId){
        courseService.addStudent(id,studentId);
        return "redirect:/courses/" + id;
    }

    @DeleteMapping("{id}/removeStudent")
    public String removeStudent(@PathVariable Long id,
                                @RequestParam Long studentId){
        courseService.removeStudent(id,studentId);
        return "redirect:/courses/" + id;
    }
}