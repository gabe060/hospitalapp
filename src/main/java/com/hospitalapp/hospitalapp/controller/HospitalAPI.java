package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospital")
public class HospitalAPI {

    private final HospitalService hospitalService;

    public HospitalAPI(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/new")
    public ResponseEntity<Hospital> saveNew(@RequestBody @Valid Hospital hospital, BindingResult result) {
        return ResponseEntity.ok(this.hospitalService.save(hospital, result));
    }

    @DeleteMapping(value = "/{hospitalId}/delete")
    public ResponseEntity<Void> delete(@PathVariable("hospitalId") Long hospitalId) {
        Hospital hospital = hospitalService.findByHospitalId(hospitalId);
        hospitalService.delete(hospital);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Hospital>> listaHospitais() {
        Iterable<Hospital> hospitais = hospitalService.findAll();
        return ResponseEntity.ok(hospitais);
    }

    @GetMapping(value = "/{hospitalId}")
    public ResponseEntity getHospitalById(@PathVariable("hospitalId") Long hospitalId) {
        Hospital hospital = hospitalService.findByHospitalId(hospitalId);
        return ResponseEntity.ok(hospital);
    }

}
