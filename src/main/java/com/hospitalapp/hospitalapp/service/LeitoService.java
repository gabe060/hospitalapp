package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.projection.HistoricoInternacaoLeitoProjection;
import com.hospitalapp.hospitalapp.projection.InfoPacienteProjection;
import com.hospitalapp.hospitalapp.repository.LeitoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Leito findByLeitoIdAndHospitalId(Long leitoId, Long hospitalId) {
        return this.leitoRepository.findByLeitoIdAndQuartoAlaHospitalHospitalId(leitoId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não encontrado: " + leitoId + ". Verifique o ID informado!"));
    }

    @Transactional
    public Leito findFirstLeitoLiberadoByEspecialidadeAndHospitalId(String especialidade, Long hospitalId) {
        return this.leitoRepository.findFirstByQuartoAlaEspecialidadeIgnoreCaseAndQuartoAlaHospitalHospitalIdAndStatus(especialidade, hospitalId, StatusEnum.LIBERADO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum leito liberado encontrado para a especialidade '" + especialidade + "' no hospital de ID " + hospitalId));
    }

    @Transactional
    public Leito findByPacientePacienteIdAndHospitalId(Long pacienteId, Long hospitalId) {
        return this.leitoRepository.findByPacientePacienteIdAndQuartoAlaHospitalHospitalId(pacienteId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não encontrado para o paciente de ID " + pacienteId));
    }

    @Transactional
    public Iterable<Leito> findByEspecialidadeLiberadoAndHospitalId(String especialidade, Long hospitalId) {
        return this.leitoRepository.findByStatusAndQuartoAlaEspecialidadeAndQuartoAlaHospitalHospitalId(StatusEnum.LIBERADO, especialidade, hospitalId);
    }



    @Transactional
    public Iterable<Leito> findAllByHospitalId(Long hospitalId) {
        Iterable<Leito> iterable = leitoRepository.findAllByQuartoAlaHospitalHospitalId(hospitalId);
        List<Leito> leitos = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

        if (leitos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há leitos cadastrados no hospital de ID " + hospitalId);
        }

        return leitos;
    }

    @Transactional
    public List<HistoricoInternacaoLeitoProjection> getHistoricoByCodigoLeito(String codigoLeito, Long hospitalId) {
        List<HistoricoInternacaoLeitoProjection> historico = leitoRepository.findHistoricoByCodigoLeito(codigoLeito, hospitalId);
        if (historico.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum histórico encontrado para o leito " + codigoLeito + " no hospital de ID " + hospitalId);
        }
        return historico;
    }
}
