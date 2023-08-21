package zhanuzak.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import zhanuzak.exceptions.CustomNotFoundException;
import zhanuzak.models.Hospital;
import zhanuzak.repository.HospitalRepository;

import java.util.List;

@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Hospital> findAll() {
        return entityManager.createQuery("select h from Hospital  h", Hospital.class).
                getResultList();
    }

    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public Hospital findById(Long hospitalId) {
        Hospital hospitalId1 = entityManager.createQuery("select h from  Hospital  h where h.id=:hospitalId",
                Hospital.class).setParameter("hospitalId", hospitalId).getSingleResult();
        if(hospitalId1==null){
            throw new CustomNotFoundException("Hospital with id "+hospitalId+ " not found");
        }
        return hospitalId1;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Hospital h where h.id=:id").
                setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Hospital> findAllByName(String name) {
        List<Hospital> name1 = entityManager.createQuery("select h from  Hospital  h where h.name=:name or h.address=:name",
                Hospital.class).setParameter("name", name).getResultList();
        return name1;
    }

    @Override
    public List<Hospital> findAllByAddressAndName(String address, String name) {
        return entityManager.createQuery("select h from Hospital  h where h.address=:address and h.name=:name",Hospital.class).
                setParameter("address",address).setParameter("name",name).getResultList();
    }

    @Override
    public void merge(Hospital hospital) {
        entityManager.merge(hospital);
    }
}
