package cl.praxis.escuelarural_jpa.service;


import cl.praxis.escuelarural_jpa.entity.Course;

import java.util.List;

public interface ICourseService {

    Course getCourse(Long id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long id);

    void addStudent(Long id, Long studentId);

    void removeStudent(Long id, Long studentId);
}
