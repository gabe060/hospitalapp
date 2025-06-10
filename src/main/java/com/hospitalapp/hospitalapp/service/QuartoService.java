package com.hospitalapp.hospitalapp.service;

import com.hospitalapp.hospitalapp.model.Quarto;
import com.hospitalapp.hospitalapp.projection.QuartoInfoByEspecialidadeProjection;
import com.hospitalapp.hospitalapp.projection.QuartoWithLeitoLiberadoProjection;
import com.hospitalapp.hospitalapp.repository.QuartoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @Transactional
    public Iterable<Quarto> findAll() {
        return this.quartoRepository.findAll();
    }

    @Transactional
    public Quarto findByQuartoIdAndHospitalId(Long quartoId, Long hospitalId) {
        return this.quartoRepository.findByQuartoIdAndAlaHospitalHospitalId(quartoId, hospitalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado: " + quartoId + ". Verifique o ID informado!"));
    }

    @Transactional
    public List<QuartoInfoByEspecialidadeProjection> getInfoQuartosByEspecialidade(Long hospitalId) {
        List<QuartoInfoByEspecialidadeProjection> infoQuartos = this.quartoRepository.findInfoQuartosByEspecialidade(hospitalId);

        if (infoQuartos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há quartos cadastrados no hospital de ID " + hospitalId);
        }

        return infoQuartos;
    }

    @Transactional
    public List<QuartoWithLeitoLiberadoProjection> getQuartoWithLeitoDisponivel(Long hospitalId) {
        List<QuartoWithLeitoLiberadoProjection> quartosLeitoDisponivel = this.quartoRepository.findQuartosWithLeitoLiberado(hospitalId);

        if (quartosLeitoDisponivel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há quartos com leitos liberados no hospital de ID " + hospitalId);
        }

        return quartosLeitoDisponivel;
    }

}
