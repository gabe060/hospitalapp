package com.hospitalapp.hospitalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalapp.hospitalapp.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Leito {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long leitoId;

    @Column(name = "codigo")
    private String codigoLeito;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do leito não pode ser vazio")
    @Column(name = "status")
    private StatusEnum status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
