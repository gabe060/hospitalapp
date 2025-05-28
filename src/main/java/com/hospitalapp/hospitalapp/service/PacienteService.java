package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public Iterable<Paciente> findAll() {
        return this.pacienteRepository.findAll();
    }

    @Transactional
    public Paciente findByPacienteId(long pacienteId) {
        return this.pacienteRepository.findByPacienteId(pacienteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado: " + pacienteId + ". Verifique o ID informado!"));
    }

}
