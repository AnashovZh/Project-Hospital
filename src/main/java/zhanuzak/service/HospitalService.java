package zhanuzak.service;

import org.springframework.stereotype.Service;
import zhanuzak.models.Hospital;

import java.util.List;

@Service
public interface HospitalService {
    List<Hospital> findAll();

    void save(Hospital hospital);

    Hospital findById(Long hospitalId);

    void update(Long hospitalId, Hospital newHospital);

    void deleteById(Long hospitalId);

    List<Hospital> findAll(String name, String address);
}
