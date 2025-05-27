package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Ala;
import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.repository.AlaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlaService {

    private final AlaRepository alaRepository;

    public AlaService(AlaRepository alaRepository) {
        this.alaRepository = alaRepository;
    }


    @Transactional
    public Iterable<Ala> findAll() {
        return this.alaRepository.findAll();
    }

    @Transactional
    public Ala findByAlaId(long alaId) {
        return this.alaRepository.findByAlaId(alaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ala não encontrada: " + alaId + ". Verifique o ID informado!"));
    }


    public void delete(Ala ala){
         if (!ala.getQuartos().isEmpty()) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta ala não pode ser deletada pois possui quartos associados.");
         }

        this.alaRepository.delete(ala);
    }

}
