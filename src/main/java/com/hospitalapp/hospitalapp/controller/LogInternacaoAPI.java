package com.hospitalapp.hospitalapp.controller;

import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.LogInternacao;
import com.hospitalapp.hospitalapp.service.LeitoService;
import com.hospitalapp.hospitalapp.service.LogInternacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospital/{hospitalId}/log/")
public class LogInternacaoAPI {


    private final LogInternacaoService logInternacaoService;

    public LogInternacaoAPI(LogInternacaoService logInternacaoService) {
        this.logInternacaoService = logInternacaoService;
    }

    @GetMapping("/leito/{leitoId}")
    public ResponseEntity getLogByLeitoId(@PathVariable("hospitalId") Long hospitalId, @PathVariable("leitoId") Long leitoId) {
        List<LogInternacao> logInternacao = logInternacaoService.findLogByLeitoId(leitoId);
        return ResponseEntity.ok(logInternacao);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity getLogByPacienteId(@PathVariable("hospitalId") Long hospitalId, @PathVariable("pacienteId") Long pacienteId) {
        List<LogInternacao> logInternacao = logInternacaoService.findLogByPacienteId(pacienteId);
        return ResponseEntity.ok(logInternacao);
    }
}