package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.service.LeitoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital/{hospitalId}/leito")
public class LeitoAPI {


    private final LeitoService leitoService;

    public LeitoAPI(LeitoService leitoService) {
        this.leitoService = leitoService;
    }

    @GetMapping("/{leitoId}")
    public ResponseEntity getLeitoByIdAndHospitalId(@PathVariable("hospitalId") Long hospitalId, @PathVariable("leitoId") Long leitoId) {
        Leito leito = leitoService.findByLeitoIdAndHospitalId(leitoId, hospitalId);
        return ResponseEntity.ok(leito);
    }
}
