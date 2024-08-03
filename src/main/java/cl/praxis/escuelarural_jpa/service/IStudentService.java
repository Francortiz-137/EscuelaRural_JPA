package cl.praxis.escuelarural_jpa.service;

import cl.praxis.escuelarural_jpa.entity.Student;

import java.util.List;

public interface IStudentService {

    public Student getStudent(Long id);
    public List<Student> getAllStudents();
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public void deleteStudent(Long id);

}
