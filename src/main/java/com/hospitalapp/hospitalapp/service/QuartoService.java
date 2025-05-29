package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.repository.QuartoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @Transactional
    public Iterable<Quarto> findAll() {
        return this.quartoRepository.findAll();
    }

    @Transactional
    public Quarto findByQuartoId(long quartoId) {
        return this.quartoRepository.findByQuartoId(quartoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado: " + quartoId + ". Verifique o ID informado!"));
    }

}
