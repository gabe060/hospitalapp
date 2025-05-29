package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.dto.PacienteRequestAltaDTO;
import com.hospitalapp.hospitalapp.dto.PacienteRequestInternacaoDTO;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.service.LeitoService;
import com.hospitalapp.hospitalapp.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/hospital/{hospitalId}")
public class PacienteAPI {

    private final LeitoService leitoService;
    private final PacienteService pacienteService;

    public PacienteAPI(LeitoService leitoService, PacienteService pacienteService) {
        this.leitoService = leitoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping("/internar")
    public ResponseEntity<String> internarPaciente(@PathVariable("hospitalId") Long hospitalId, @RequestBody @Valid PacienteRequestInternacaoDTO dto) {
        String nome = dto.getNome();
        String especialidade = dto.getEspecialidade();
        Leito leito = leitoService.findFirstLeitoLiberadoByEspecialidadeAndHospitalId(especialidade, hospitalId);
        String codigoLeito = leito.getCodigoLeito();
        String codigoQuarto = leito.getQuarto().getCodigoQuarto();
        Long alaId = leito.getQuarto().getAla().getAlaId();
        String especialidadeAla = leito.getQuarto().getAla().getEspecialidade();
        pacienteService.internarPaciente(especialidade, nome, hospitalId);
        LocalDateTime dataInternacao = leito.getPaciente().getDataInternacao();


        return ResponseEntity.ok("Paciente " + nome + " foi internado na ala " + alaId + " (" + especialidadeAla + "), quarto " + codigoQuarto + ", leito " + codigoLeito + " em " + dataInternacao + ".");
    }

    @PostMapping("/alta")
    public ResponseEntity<String> altaPaciente(@PathVariable("hospitalId") Long hospitalId, @RequestBody @Valid PacienteRequestAltaDTO dto) {
        Long pacienteId = dto.getPacienteId();
        Leito leito = leitoService.findByPacientePacienteIdAndHospitalId(pacienteId, hospitalId);
        Paciente paciente = pacienteService.findByPacienteId(pacienteId);
        String nome = paciente.getNome();
        pacienteService.altaPaciente(pacienteId, leito);
        LocalDateTime dataAlta = leito.getPaciente().getDataAlta();

        return ResponseEntity.ok("Paciente " + nome + " foi liberado em " + dataAlta + ".");
    }

}
