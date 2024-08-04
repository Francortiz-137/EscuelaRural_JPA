package cl.praxis.escuelarural_jpa.controller;

import cl.praxis.escuelarural_jpa.entity.Course;
import cl.praxis.escuelarural_jpa.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/{id}")
    public String getCourse(Model model, @PathVariable Long id) {
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
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
}