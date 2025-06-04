package com.hospitalapp.hospitalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalapp.hospitalapp.enums.StatusEnum;
import jakarta.persistence.*;
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
public class Quarto {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long quartoId;

    @Column(name = "codigo")
    private String codigoQuarto;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do quarto não pode ser vazio")
    @Column(name = "status")
    private StatusEnum status;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ala_id")
    private Ala ala;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leito> leitos = new ArrayList<>();


}
