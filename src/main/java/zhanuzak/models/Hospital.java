package zhanuzak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @NotEmpty(message = "Name should not be empty")
   @Size(min = 2,max = 20,message = "Name should between 2 and 20 characters")
    private String name;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 30,message = "Address  should between 2 and 30 characters")
    private  String address;
    @OneToMany
    private List<Doctor>doctors;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Patient>patients;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Department>departments;
    @OneToMany
    private List<Appointment>appointments;

    public void addDepartment(Department department) {
        if (departments==null){
            departments=new ArrayList<>();
        }else{
            departments.add(department);
        }
    }
}
