package cl.praxis.escuelarural_jpa.repository;

import cl.praxis.escuelarural_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
