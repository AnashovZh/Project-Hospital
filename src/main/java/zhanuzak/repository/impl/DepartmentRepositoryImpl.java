package zhanuzak.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import zhanuzak.exceptions.CustomNotFoundException;
import zhanuzak.models.Department;
import zhanuzak.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("select d from Department d", Department.class)
                .getResultList();
    }

    @Override
    public List<Department> findAllHospitalId(Long hospitalId) {

        return entityManager.createQuery("select d from Department  d where d.hospital.id=:hospitalId", Department.class).
                setParameter("hospitalId", hospitalId).getResultList();
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        return Optional.ofNullable(entityManager.createQuery("select d from Department d where d.id=:d",
                Department.class).setParameter("d", departmentId).getSingleResult());
    }

    @Override
    public void save(Department department) {
        entityManager.persist(department);
    }

    @Override
    public Department findDepartmentByHospitalId(Long departmentId) {
        Department department = entityManager.createQuery(
                "select d from Department d where  d.id = :depId", Department.class)
                . setParameter("depId", departmentId).getSingleResult();
        if (department == null) {
            throw new CustomNotFoundException("Department with id: "+departmentId+ " not found !!!");
        }
        return department;
    }
}
