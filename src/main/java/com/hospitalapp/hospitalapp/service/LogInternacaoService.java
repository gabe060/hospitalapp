package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.LogInternacao;
import com.hospitalapp.hospitalapp.repository.LogInternacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogInternacaoService {

    private final LogInternacaoRepository logInternacaoRepository;

    public LogInternacaoService(LogInternacaoRepository logInternacaoRepository) {
        this.logInternacaoRepository = logInternacaoRepository;
    }

    @Transactional
    public List<LogInternacao> findLogByLeitoId(Long leitoId) {
        return this.logInternacaoRepository.findByLeitoId(leitoId);
    }

    @Transactional
    public List<LogInternacao> findLogByPacienteId(Long pacienteId) {
        return this.logInternacaoRepository.findByPacienteId(pacienteId);
    }

    @Transactional
    public void log(Long leitoId, Long pacienteId) {
        LogInternacao log = new LogInternacao();
        log.setLeitoId(leitoId);
        log.setPacienteId(pacienteId);
        log.setDataInternacao(LocalDateTime.now());
        this.logInternacaoRepository.save(log);
    }

    @Transactional
    public void logAlta(Long leitoId) {
        LogInternacao log = this.findLogByLeitoId(leitoId)
                .stream()
                .filter(l -> l.getDataAlta() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Internação não encontrada para este Paciente no Leito de ID: " + leitoId));

        log.setDataAlta(LocalDateTime.now());
        this.logInternacaoRepository.save(log);
    }

}
