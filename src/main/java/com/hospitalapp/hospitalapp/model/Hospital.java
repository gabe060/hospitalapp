package com.hospitalapp.hospitalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long hospitalId;

    @NotEmpty(message = "O nome do hospital não pode ser vazio")
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ala> alaList = new ArrayList<>();

    public void addAla(Ala ala) {
        alaList.add(ala);
    }
}
