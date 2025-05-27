package com.hospitalapp.hospitalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ala {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long alaId;

    @NotEmpty(message = "A especialidade da ala não pode ser vazia")
    @Column(name = "especialidade")
    private String especialidade;

    @NotNull(message = "A quantidade de quartos não pode ser vazia")
    @Column(name = "quantidade_quartos")
    private Integer quantQuartos;
    @NotNull(message = "A quantidade de leitos não pode ser vazia")
    @Column(name = "quantidade_leitos_por_quarto")
    private Integer quantLeitosPorQuarto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "ala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quarto> quartos = new ArrayList<>();


}
