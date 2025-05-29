package com.hospitalapp.hospitalapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LogInternacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogInternacao;

    @ManyToOne
    @JoinColumn(name = "leito_id")
    private Leito leito;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime dataInternacao;
    private LocalDateTime dataAlta;
}
