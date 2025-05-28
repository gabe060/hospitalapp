package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    Optional<Quarto> findByQuartoId(long quartoId);
}