package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.dto.LeitoLivreByEspecialidadeDTO;
import com.hospitalapp.hospitalapp.dto.NewAlaDTO;
import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.service.LeitoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital/{hospitalId}/relatorio")
public class RelatorioAPI {

    private final LeitoService leitoService;

    public RelatorioAPI(LeitoService leitoService) {
        this.leitoService = leitoService;
    }

    @GetMapping("/leitos-liberados")
    public ResponseEntity<Iterable<Leito>> getLeitoLiberadoByEspecialidadeAndHospitalId(@PathVariable("hospitalId") Long hospitalId, @RequestBody @Valid LeitoLivreByEspecialidadeDTO dto) {
        String especialidade = dto.getEspecialidade();
        Iterable<Leito> leitos = leitoService.findByEspecialidadeLiberadoAndHospitalId(especialidade, hospitalId);
            return ResponseEntity.ok(leitos);
    }
}
