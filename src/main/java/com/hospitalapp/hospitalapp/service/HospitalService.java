package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;


@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Iterable<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    @Transactional
    public Hospital findByHospitalId(long hospitalId) {
        return this.hospitalRepository.findByHospitalId(hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital não encontrado: " + hospitalId + ". Verifique o ID informado!"));
    }


    @Transactional
    public Hospital save(Hospital hospital, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar hospital, verifique os campos!");
        }

        return this.hospitalRepository.save(hospital);
    }

    public void delete(Hospital hospital) {
        this.hospitalRepository.delete(hospital);
    }

}
