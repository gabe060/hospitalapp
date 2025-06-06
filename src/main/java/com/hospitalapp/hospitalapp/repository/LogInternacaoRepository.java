package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.LogInternacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogInternacaoRepository extends JpaRepository<LogInternacao, Long> {
    List<LogInternacao> findByPacienteId(Long pacienteId);

    List<LogInternacao> findByLeitoId(Long leitoId);

}