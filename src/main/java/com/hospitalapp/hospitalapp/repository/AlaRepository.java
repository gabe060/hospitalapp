package com.hospitalapp.hospitalapp.repository;

import com.hospitalapp.hospitalapp.model.Ala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlaRepository extends JpaRepository<Ala, Long> {
    Optional<Ala> findByAlaId(@Param("alaId") Long alaId);

}
