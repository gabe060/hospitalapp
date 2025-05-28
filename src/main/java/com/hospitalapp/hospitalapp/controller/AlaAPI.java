package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.dto.AlaRequestDTO;
import com.hospitalapp.hospitalapp.model.Ala;
import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.service.AlaService;
import com.hospitalapp.hospitalapp.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospital/ala")
public class AlaAPI {

    private final AlaService alaService;
    private final HospitalService hospitalService;

    public AlaAPI(AlaService alaService, HospitalService hospitalService) {
        this.alaService = alaService;
        this.hospitalService = hospitalService;
    }


    @PostMapping("/new")
    public ResponseEntity<Hospital> newAla(@RequestBody @Valid AlaRequestDTO dto) {
        Hospital hospital = hospitalService.findByHospitalId(dto.getHospitalId());
        alaService.newAla(dto);
        return ResponseEntity.ok(hospital);
    }

    @DeleteMapping(value = "/{alaId}/delete")
    public ResponseEntity<Void> delete(@PathVariable("alaId") Long alaId) {
        Ala ala = alaService.findByAlaId(alaId);
        alaService.delete(ala);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{alaId}")
    public ResponseEntity getAlaById(@PathVariable("alaId") Long alaId) {
        Ala ala = alaService.findByAlaId(alaId);
        return ResponseEntity.ok(ala);
    }
}
