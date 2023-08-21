package zhanuzak.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhanuzak.exceptions.CustomNotFoundException;
import zhanuzak.models.Hospital;
import zhanuzak.repository.HospitalRepository;
import zhanuzak.service.HospitalService;

import java.util.List;

@Service

@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital findById(Long hospitalId) {
        Hospital byId = hospitalRepository.findById(hospitalId);
        if (byId == null) {
            throw new CustomNotFoundException("Hospital with id: " + hospitalId + "not found !!!");
        }
        return byId;
    }

    @Transactional
    @Override
    public void update(Long hospitalId, Hospital newHospital) {
        Hospital hospital = findById(hospitalId);
        hospital.setAddress(newHospital.getAddress());
        hospital.setName(newHospital.getName());

    }

    @Override
    public void deleteById(Long hospitalId) {
        hospitalRepository.deleteById(findById(hospitalId).getId());
    }

    @Override
    public List<Hospital> findAll(String name, String address) {
        if (name != null & address != null) return hospitalRepository.findAllByName(name);
        else if (address != null & name != null) return hospitalRepository.findAllByAddressAndName(address, name);
        return hospitalRepository.findAll();
    }
}
