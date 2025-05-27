package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Hospital;
import com.hospitalapp.hospitalapp.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
