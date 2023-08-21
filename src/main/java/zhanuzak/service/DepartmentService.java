package zhanuzak.service;

import zhanuzak.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    List<Department> findAllHospitalId(Long hospitalId);

    void save(Long hospitalId, Department department);
    Department findById(Long departmentId);

    void save(Department department);

    void update(Department newDepartment, Long departmentId);

    Department findDepartmentById(Long departmentId);

    void updateById(Department newDepartment, Long departmentId);
}
