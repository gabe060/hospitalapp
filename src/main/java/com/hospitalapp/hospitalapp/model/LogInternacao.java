package com.hospitalapp.hospitalapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "log_internacao")
public class LogInternacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogInternacao;

    @JoinColumn(name = "leito_id")
    private Long leitoId;

    @JoinColumn(name = "paciente_id")
    private Long pacienteId;

    private LocalDateTime dataInternacao;
    private LocalDateTime dataAlta;

}
