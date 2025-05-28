package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.repository.LeitoRepository;
import com.hospitalapp.hospitalapp.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LeitoService {

    private final LeitoRepository leitoRepository;

    public LeitoService(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }


    @Transactional
    public Iterable<Leito> findAll() {
        return this.leitoRepository.findAll();
    }

    @Transactional
    public Leito findByLeitoId(long leitoId) {
        return this.leitoRepository.findByLeitoId(leitoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não encontrado: " + leitoId + ". Verifique o ID informado!"));
    }
}
