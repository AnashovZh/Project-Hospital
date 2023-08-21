package zhanuzak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "field can not be empty")
    private String name;
    @ManyToOne
    private Hospital hospital;
    @ManyToMany(mappedBy = "departments")
    private List<Doctor>doctors;

}
