package com.hospitalapp.hospitalapp.dto;

import com.hospitalapp.hospitalapp.model.Ala;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDTO {

    private Long hospitalId;

    @NotEmpty(message = "O nome do hospital não pode ser vazio")
    private String nome;

    private List<Ala> alaList = new ArrayList<>();
}
