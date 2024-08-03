package cl.praxis.escuelarural_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cursos")
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "titulo", nullable = false)
    private String title;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "creditos", nullable = false)
    private float credits;
    private boolean deleted = false;
}
