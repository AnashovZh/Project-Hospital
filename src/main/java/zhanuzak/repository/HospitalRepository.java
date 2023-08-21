package zhanuzak.repository;

import zhanuzak.models.Hospital;

import java.util.List;

public interface HospitalRepository {
   List<Hospital>  findAll();

    void save(Hospital hospital);

   Hospital findById(Long hospitalId);


    void deleteById(Long id);

    List<Hospital> findAllByName(String name);

    List<Hospital> findAllByAddressAndName(String address,String name);

    void merge(Hospital hospital);
}
