package zhanuzak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zhanuzak.enums.Gender;

import java.util.List;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
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
    @NotBlank(message = "phone number must not be blank")
    @Pattern(regexp = "^(?!\\+996)\\d+$", message = "phone number must not start with +996")
    @Size(max = 13, message = "phone number must not 13 character")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message = "choose just male or female")
    private Gender gender;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @ManyToOne
    private Hospital hospital;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}

