package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Leito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    Optional<Leito> findByLeitoId(long leitoId);
}
