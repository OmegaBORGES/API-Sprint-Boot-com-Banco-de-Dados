package com.agenciaviagens.api.service;

import com.agenciaviagens.api.entity.AtividadeTuristica;
import com.agenciaviagens.api.repository.AtividadeTuristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeTuristicaService {

    @Autowired
    private AtividadeTuristicaRepository atividadeRepository;

    public List<AtividadeTuristica> listarTodos() {
        return atividadeRepository.findAll();
    }

    public Optional<AtividadeTuristica> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    public AtividadeTuristica salvar(AtividadeTuristica atividade) {
        return atividadeRepository.save(atividade);
    }

    public Optional<AtividadeTuristica> atualizar(Long id, AtividadeTuristica atividade) {
        return atividadeRepository.findById(id).map(existing -> {
            existing.setNome(atividade.getNome());
            existing.setDescricao(atividade.getDescricao());
            existing.setPreco(atividade.getPreco());
            existing.setDuracaoHoras(atividade.getDuracaoHoras());
            existing.setDestino(atividade.getDestino());
            return atividadeRepository.save(existing);
        });
    }

    public boolean deletar(Long id) {
        if (atividadeRepository.existsById(id)) {
            atividadeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
