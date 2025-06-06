package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.LogInternacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogInternacaoRepository extends JpaRepository<LogInternacao, Long> {
    List<LogInternacao> findByPacienteId(@Param("pacienteId") Long pacienteId);

    List<LogInternacao> findByLeitoId(@Param("leitoId") Long leitoId);


}