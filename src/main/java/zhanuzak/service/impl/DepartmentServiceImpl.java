package zhanuzak.service.impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhanuzak.exceptions.CustomNotFoundException;
import zhanuzak.models.Department;
import zhanuzak.models.Hospital;
import zhanuzak.repository.DepartmentRepository;
import zhanuzak.repository.HospitalRepository;
import zhanuzak.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private  final HospitalRepository hospitalRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findAllHospitalId(Long hospitalId) {
        return departmentRepository.findAllHospitalId(hospitalId);
    }

    @Override
    public void save(Long hospitalId, Department department) {
        Hospital hospital = hospitalRepository.findById(hospitalId);
        if (hospital==null){
            throw  new CustomNotFoundException("Hospital with id: "+hospitalId+ " not found");
        }
        department.setHospital(hospital);
        hospital.addDepartment(department);
        hospitalRepository.merge(hospital);
    }

    @Override
    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(()->new RuntimeException("Department with id:"+departmentId+" not found !"));
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    @Override
    public void update(Department newDepartment, Long departmentId) {
        Department departmentById = findById(departmentId);
        departmentById.setName(newDepartment.getName());
    }

    @Override
    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findDepartmentByHospitalId(departmentId);
    }

    @Override
    @Transactional
    public void updateById(Department newDepartment, Long departmentId) {
        Department departmentByHospitalId = findDepartmentById(departmentId);
        if (departmentByHospitalId==null){
            throw new CustomNotFoundException("Department with id: "+departmentId+" not found !!! ");
        }else {
            departmentByHospitalId.setName(newDepartment.getName());
        }
    }
}
