package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.dto.PacienteRequestDTO;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.service.LeitoService;
import com.hospitalapp.hospitalapp.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital/{hospitalId}/ala/{alaId}/quarto/{quartoId}/leito/{leitoId}/paciente")
public class PacienteAPI {

    private final LeitoService leitoService;
    private final PacienteService pacienteService;

    public PacienteAPI(LeitoService leitoService, PacienteService pacienteService) {
        this.leitoService = leitoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping("/internar")
    public ResponseEntity<String> internarPaciente(@PathVariable("leitoId") Long leitoId, @RequestBody @Valid PacienteRequestDTO dto) {
        Leito leito = leitoService.findByLeitoId(leitoId);
        String nome = dto.getNome();
        String codigoLeito = leito.getCodigoLeito();
        pacienteService.internarPaciente(leitoId, nome);

        return ResponseEntity.ok("Paciente " + nome + " foi internado no leito " + codigoLeito + ".");
    }

}
