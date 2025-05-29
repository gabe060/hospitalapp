package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.repository.LeitoRepository;
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

    @Transactional
    public Leito findFirstLeitoLiberadoByEspecialidadeAndHospitalId(String especialidade, Long hospitalId) {
        return this.leitoRepository.findFirstByQuartoAlaEspecialidadeIgnoreCaseAndQuartoAlaHospitalHospitalIdAndStatus(especialidade, hospitalId, StatusEnum.LIBERADO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum leito liberado encontrado para a especialidade '" + especialidade + "' no hospital de ID " + hospitalId));
    }

    @Transactional
    public Leito findByPacientePacienteIdAndHospitalId(Long pacienteId, Long hospitalId) {
        return this.leitoRepository.findByPacientePacienteIdAndQuartoAlaHospitalHospitalId(pacienteId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não encontrado para o paciente de ID " + pacienteId));
    }


}
