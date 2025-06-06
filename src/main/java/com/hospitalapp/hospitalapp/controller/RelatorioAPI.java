package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.dto.EspecialidadeDTO;
import com.hospitalapp.hospitalapp.dto.PacienteIdDTO;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.projection.*;
import com.hospitalapp.hospitalapp.service.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospital/{hospitalId}/relatorio")
public class RelatorioAPI {

    private final LeitoService leitoService;
    private final QuartoService quartoService;
    private final PacienteService pacienteService;
    private final AlaService alaService;
    private final HospitalService hospitalService;

    public RelatorioAPI(LeitoService leitoService, QuartoService quartoService, PacienteService pacienteService, AlaService alaService, HospitalService hospitalService) {
        this.leitoService = leitoService;
        this.quartoService = quartoService;
        this.pacienteService = pacienteService;
        this.alaService = alaService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/leitos-liberados")
    public ResponseEntity<Iterable<Leito>> getLeitoLiberadoByEspecialidadeAndHospitalId(@PathVariable("hospitalId") Long hospitalId, @RequestBody @Valid EspecialidadeDTO dto) {
        String especialidade = dto.getEspecialidade();
        Iterable<Leito> leitos = leitoService.findByEspecialidadeLiberadoAndHospitalId(especialidade, hospitalId);
            return ResponseEntity.ok(leitos);
    }

    @GetMapping("/leitos-todos")
    public ResponseEntity<Iterable<Leito>> getAllLeitos(@PathVariable("hospitalId") Long hospitalId) {
        Iterable<Leito> leitos = leitoService.findAllByHospitalId(hospitalId);
        return ResponseEntity.ok(leitos);
    }

    @GetMapping("/paciente-quarto/{pacienteId}")
    public ResponseEntity<PacienteQuartoProjection> getQuartoByPacienteId(@PathVariable("hospitalId") Long hospitalId, @PathVariable("pacienteId") Long pacienteId) {
        PacienteQuartoProjection quarto = pacienteService.getQuartoPaciente(pacienteId, hospitalId);
        return ResponseEntity.ok(quarto);
    }

    @GetMapping("/paciente-info/{pacienteId}")
    public ResponseEntity<InfoPacienteProjection> getInfoPaciente(@PathVariable("hospitalId") Long hospitalId, @PathVariable("pacienteId") Long pacienteId) {
        InfoPacienteProjection quarto = pacienteService.getInfoPaciente(pacienteId, hospitalId);
        return ResponseEntity.ok(quarto);
    }

    @GetMapping("/quartos-info-especialidade")
    public ResponseEntity<List<QuartoInfoByEspecialidadeProjection>> getQuartoInfoByEspecialidade(@PathVariable("hospitalId") Long hospitalId) {
        List<QuartoInfoByEspecialidadeProjection> quartosInfo = quartoService.getInfoQuartosByEspecialidade(hospitalId);
        return ResponseEntity.ok(quartosInfo);
    }

    @GetMapping("/quartos-leitos-liberados")
    public ResponseEntity<List<QuartoWithLeitoLiberadoProjection>> getQuartoWithLeitoLiberado(@PathVariable("hospitalId") Long hospitalId) {
        List<QuartoWithLeitoLiberadoProjection> quartosInfo = quartoService.getQuartoWithLeitoDisponivel(hospitalId);
        return ResponseEntity.ok(quartosInfo);
    }

    @GetMapping("/pacientes-internados")
    public ResponseEntity<List<PacientesInternadosAlfabeticoProjection>> getPacientesInternadosAlfabeticamente (@PathVariable("hospitalId") Long hospitalId) {
        List<PacientesInternadosAlfabeticoProjection> pacientesInternados = pacienteService.getPacientesInternadosAlfabeticamente(hospitalId);
        return ResponseEntity.ok(pacientesInternados);
    }

    @GetMapping("/leito-historico/{codigoLeito}")
    public ResponseEntity<List<HistoricoInternacaoLeitoProjection>> getHistoricoLeito (@PathVariable("hospitalId") Long hospitalId, @PathVariable("codigoLeito") String codigoLeito) {
        List<HistoricoInternacaoLeitoProjection> historico = leitoService.getHistoricoByCodigoLeito(codigoLeito, hospitalId);
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/paciente-historico/{pacienteId}/page/{page}")
    public ResponseEntity<Page<HistoricoInternacaoPacienteProjection>> getHistoricoPacientePaginavel (@PathVariable("hospitalId") Long hospitalId, @PathVariable("pacienteId") Long pacienteId, @PathVariable("page") int page) {
        Page<HistoricoInternacaoPacienteProjection> historico = pacienteService.getHistoricoPaciente(pacienteId, hospitalId, page);
        return ResponseEntity.ok(historico);
    }
}
