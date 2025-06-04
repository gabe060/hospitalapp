package com.hospitalapp.hospitalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long pacienteId;

    @NotEmpty(message = "O nome do paciente não pode ser vazio")
    @Column(name = "nome")
    private String nome;

    @Column(name = "internacao")
    private LocalDateTime dataInternacao;

    @Column(name = "alta")
    private LocalDateTime dataAlta;


}
