package zhanuzak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "field can not be empty")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "field can not be empty")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "field can not be empty")
    private String position;
    @NotNull(message = "field can not be empty")
    @Email
    @Size(max = 100,message = "email must not exceed 100 characters")
    private String  email;
    @ManyToMany
    private List<Department>departments;
    @OneToMany(mappedBy = "doctor")
    private List<Appointment>appointments;

}
