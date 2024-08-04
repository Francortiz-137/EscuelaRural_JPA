package cl.praxis.escuelarural_jpa.service.impl;

import cl.praxis.escuelarural_jpa.entity.Course;
import cl.praxis.escuelarural_jpa.entity.Student;
import cl.praxis.escuelarural_jpa.repository.ICourseRepository;
import cl.praxis.escuelarural_jpa.repository.IStudentRepository;
import cl.praxis.escuelarural_jpa.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private ICourseRepository courseRepository;
    private IStudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(ICourseRepository courseRepository, IStudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void addStudent(Long id, Long studentId) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + studentId));
        student.setCourse(course);
        studentRepository.save(student);
        course.addStudent(student);
        courseRepository.save(course);
    }

    @Override
    public void removeStudent(Long id, Long studentId) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + studentId));
        student.setCourse(null);
        studentRepository.save(student);
        course.removeStudent(student);
        courseRepository.save(course);
    }
}
