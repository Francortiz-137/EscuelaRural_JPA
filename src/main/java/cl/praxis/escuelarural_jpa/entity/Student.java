package cl.praxis.escuelarural_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estudiantes")
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String firstName;
    @Column(name = "apellido", nullable = false)
    private String lastName;
    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="course_id")
    private Course course;
}
