package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.repository.LeitoRepository;
import com.hospitalapp.hospitalapp.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final LeitoRepository leitoRepository;

    public PacienteService(PacienteRepository pacienteRepository, LeitoRepository leitoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.leitoRepository = leitoRepository;
    }

    @Transactional
    public Iterable<Paciente> findAll() {
        return this.pacienteRepository.findAll();
    }

    @Transactional
    public Paciente findByPacienteId(long pacienteId) {
        return this.pacienteRepository.findByPacienteId(pacienteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado: " + pacienteId + ". Verifique o ID informado!"));
    }

    @Transactional
    public Leito findByLeitoId(long leitoId) {
        return this.leitoRepository.findByLeitoId(leitoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não encontrado: " + leitoId + ". Verifique o ID informado!"));
    }

    @Transactional
    public void internarPaciente(Long leitoId, String nome) {
        Leito leito = this.findByLeitoId(leitoId);
        if (leito.getStatus() == (StatusEnum.LIBERADO)) {
            Paciente paciente = new Paciente();
            paciente.setNome(nome);
            leito.setStatus(StatusEnum.OCUPADO);
            leito.setPaciente(paciente);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Leito já ocupado");
        }

        this.leitoRepository.save(leito);
    }

}
