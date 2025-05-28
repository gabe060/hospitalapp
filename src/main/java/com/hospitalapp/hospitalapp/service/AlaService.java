package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.dto.AlaRequestDTO;
import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Ala;
import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.repository.AlaRepository;
import com.hospitalapp.hospitalapp.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlaService {

    private final AlaRepository alaRepository;
    private final HospitalRepository hospitalRepository;

    public AlaService(AlaRepository alaRepository, HospitalRepository hospitalRepository) {
        this.alaRepository = alaRepository;
        this.hospitalRepository = hospitalRepository;
    }


    @Transactional
    public Iterable<Ala> findAll() {
        return this.alaRepository.findAll();
    }

    @Transactional
    public Ala findByAlaId(long alaId) {
        return this.alaRepository.findByAlaId(alaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ala não encontrada: " + alaId + ". Verifique o ID informado!"));
    }

    @Transactional
    public Hospital findByHospitalId(long hospitalId) {
        return this.hospitalRepository.findByHospitalId(hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital não encontrado: " + hospitalId + ". Verifique o ID informado!"));
    }

    public void delete(Ala ala) {
        if (!ala.getQuartos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta ala não pode ser deletada pois possui quartos associados.");
        }
        this.alaRepository.delete(ala);
    }


    @Transactional
    public Hospital newAla(AlaRequestDTO dto) {
        Hospital hospital = this.findByHospitalId(dto.getHospitalId());
        Ala ala = new Ala();
        ala.setHospital(hospital);
        ala.setEspecialidade(dto.getEspecialidade());

        List<Quarto> quartosList = new ArrayList<>();

        ala = alaRepository.save(ala);

        for (int quartos = 1; quartos <= (dto.getQuantidadeQuartos()); quartos++) {
            Quarto quarto = new Quarto();
            quarto.setStatus(StatusEnum.LIBERADO);
            String codigoQuarto = ala.getEspecialidade().substring(0, 3).toUpperCase() + quartos;
            quarto.setCodigoQuarto(codigoQuarto);
            quarto.setAla(ala);
            List<Leito> leitos = new ArrayList<>();

            for (int i = 1; i <= (dto.getQuantidadeLeitosPorQuarto()); i++) {
                Leito leito = new Leito();
                leito.setStatus(StatusEnum.LIBERADO);
                String codigoLeito = codigoQuarto + "-" + i;
                leito.setCodigoLeito(codigoLeito);
                leito.setQuarto(quarto);
                leitos.add(leito);
            }
            quarto.setLeitos(leitos);
            quartosList.add(quarto);
        }

        ala.setQuartos(quartosList);
        hospital.addAla(ala);
        return this.hospitalRepository.save(hospital);
    }

}
