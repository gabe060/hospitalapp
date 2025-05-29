package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.service.QuartoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital/{hospitalId}/quarto")
public class QuartoAPI {

    private final QuartoService quartoService;

    public QuartoAPI(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping("/{quartoId}")
    public ResponseEntity getQuartoById(@PathVariable("hospitalId") Long hospitalId, @PathVariable("quartoId") Long quartoId) {
        Quarto quarto = quartoService.findByQuartoIdAndHospitalId(quartoId, hospitalId);
        return ResponseEntity.ok(quarto);
    }

}
