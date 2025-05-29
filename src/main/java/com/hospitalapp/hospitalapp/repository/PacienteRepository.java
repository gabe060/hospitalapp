package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByPacienteId(long pacienteId);
}
