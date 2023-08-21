package zhanuzak.repository;

import zhanuzak.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    List<Department> findAll();

    List<Department> findAllHospitalId(Long hospitalId);

   Optional<Department> findById(Long departmentId);

    void save(Department department);

    Department findDepartmentByHospitalId(Long departmentId);

}
