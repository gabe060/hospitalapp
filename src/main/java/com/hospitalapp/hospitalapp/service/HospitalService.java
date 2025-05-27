package com.hospitalapp.hospitalapp.service;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final AlaRepository alaRepository;

    public HospitalService(HospitalRepository hospitalRepository, AlaRepository alaRepository) {
        this.hospitalRepository = hospitalRepository;
        this.alaRepository = alaRepository;
    }

    @Transactional
    public Iterable<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    @Transactional
    public Hospital findByHospitalId(long hospitalId) {
        return this.hospitalRepository.findByHospitalId(hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital não encontrado: " + hospitalId + ". Verifique o ID informado!"));
    }


    @Transactional
    public Hospital save(Hospital hospital, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar hospital, verifique os campos!");
        }

        return this.hospitalRepository.save(hospital);
    }

    public void delete(Hospital hospital) {
//        for (Sessao sessao : filme.getSessaoList()) {
//            if (sessao.getAssentos() != null && !sessao.getAssentos().isEmpty()) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                        "Este filme não pode ser deletado pois possui assentos reservados em uma ou mais sessões.");
//            }
//        }
        this.hospitalRepository.delete(hospital);
    }



//    Hospital, ala, quarto, leito, paciente

    @Transactional
    public Hospital newAla(Long hospitalId, Ala ala) {
        Hospital hospital = this.findByHospitalId(hospitalId);
        ala.setHospital(hospital);
        List<Quarto> quartosList = new ArrayList<>();

        ala = alaRepository.save(ala);

        for (int quartos = 1; quartos <= (ala.getQuantQuartos()); quartos++) {
            Quarto quarto = new Quarto();
            quarto.setStatus(StatusEnum.LIBERADO);
            String codigoQuarto = ala.getEspecialidade().substring(0, 3).toUpperCase() + quartos;
            quarto.setCodigoQuarto(codigoQuarto);
            quarto.setAla(ala);
            List<Leito> leitos = new ArrayList<>();

            for (int i = 1; i <= (ala.getQuantLeitosPorQuarto()); i++) {
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
