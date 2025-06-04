package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.enums.StatusEnum;
import com.hospitalapp.hospitalapp.model.Leito;
import com.hospitalapp.hospitalapp.model.Paciente;
import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.projection.HistoricoInternacaoPacienteProjection;
import com.hospitalapp.hospitalapp.projection.InfoPacienteProjection;
import com.hospitalapp.hospitalapp.projection.PacienteQuartoProjection;
import com.hospitalapp.hospitalapp.projection.PacientesInternadosAlfabeticoProjection;
import com.hospitalapp.hospitalapp.repository.LeitoRepository;
import com.hospitalapp.hospitalapp.repository.LogInternacaoRepository;
import com.hospitalapp.hospitalapp.repository.PacienteRepository;
import com.hospitalapp.hospitalapp.repository.QuartoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final LeitoRepository leitoRepository;
    private final QuartoRepository quartoRepository;
    private final LogInternacaoRepository logInternacaoRepository;

    public PacienteService(PacienteRepository pacienteRepository, LeitoRepository leitoRepository, QuartoRepository quartoRepository, LogInternacaoRepository logInternacaoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.leitoRepository = leitoRepository;
        this.quartoRepository = quartoRepository;
        this.logInternacaoRepository = logInternacaoRepository;
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
    public Leito findFirstLeitoLiberadoByEspecialidadeAndHospitalId(String especialidade, Long hospitalId) {
        return this.leitoRepository.findFirstByQuartoAlaEspecialidadeIgnoreCaseAndQuartoAlaHospitalHospitalIdAndStatus(especialidade, hospitalId, StatusEnum.LIBERADO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum leito liberado encontrado para a especialidade '" + especialidade + "' no hospital de ID " + hospitalId));
    }

    @Transactional
    public void internarPaciente(String especialidade, String nome, Long hospitalId) {
        Leito leito = this.findFirstLeitoLiberadoByEspecialidadeAndHospitalId(especialidade, hospitalId);
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setDataInternacao(java.time.LocalDateTime.now());
        leito.setStatus(StatusEnum.OCUPADO);
        leito.setPaciente(paciente);

        this.leitoRepository.save(leito);

        Quarto quarto = leito.getQuarto();
        boolean todosOcupados = quarto.getLeitos().stream()
                .allMatch(l -> l.getStatus() == StatusEnum.OCUPADO);
        if (todosOcupados) {
            quarto.setStatus(StatusEnum.OCUPADO);
            quartoRepository.save(quarto);
        }

    }

    @Transactional
    public void altaPaciente(Long pacienteId, Leito leito) {
        Paciente paciente = this.findByPacienteId(pacienteId);
        paciente.setDataAlta(java.time.LocalDateTime.now());
        leito.setStatus(StatusEnum.LIBERADO);
        leito.setPaciente(paciente);

        this.leitoRepository.save(leito);

        Quarto quarto = leito.getQuarto();
        boolean algumLiberado = quarto.getLeitos().stream()
                .anyMatch(l -> l.getStatus() == StatusEnum.LIBERADO);
        if (algumLiberado) {
            quarto.setStatus(StatusEnum.LIBERADO);
            quartoRepository.save(quarto);
        }
    }

    @Transactional
    public List<PacientesInternadosAlfabeticoProjection> getPacientesInternadosAlfabeticamente(Long hospitalId) {
        List<PacientesInternadosAlfabeticoProjection> pacientesInternados = pacienteRepository.findPacientesInternadosAlfabeticamenteByHospitalId(hospitalId);

        if (pacientesInternados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há pacientes internados no hospital de ID " + hospitalId);
        }

        return pacientesInternados;
    }

    @Transactional
    public Page<HistoricoInternacaoPacienteProjection> getHistoricoPaciente(Long pacienteId, Long hospitalId, int page) {
        Pageable pageable = PageRequest.of(page, 1, Sort.by("dataInternacao").descending());
        return pacienteRepository.findHistoricoByPacienteId(pacienteId, hospitalId, pageable);
    }

    @Transactional
    public InfoPacienteProjection getInfoPaciente(Long pacienteId, Long hospitalId) {
        return leitoRepository.findInfoPacienteByPacienteId(pacienteId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não está internado"));
    }

    @Transactional
    public PacienteQuartoProjection getQuartoPaciente(Long pacienteId, Long hospitalId) {
        return leitoRepository.findQuartoPaciente(pacienteId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não está internado"));
    }

}
