package com.agenciaviagens.api.service;

import com.agenciaviagens.api.entity.PacoteViagem;
import com.agenciaviagens.api.repository.PacoteViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacoteViagemService {

    @Autowired
    private PacoteViagemRepository pacoteViagemRepository;

    public List<PacoteViagem> listarTodos() {
        return pacoteViagemRepository.findAll();
    }

    public Optional<PacoteViagem> buscarPorId(Long id) {
        return pacoteViagemRepository.findById(id);
    }

    public PacoteViagem salvar(PacoteViagem pacote) {
        return pacoteViagemRepository.save(pacote);
    }

    public Optional<PacoteViagem> atualizar(Long id, PacoteViagem pacote) {
        return pacoteViagemRepository.findById(id).map(existing -> {
            existing.setNome(pacote.getNome());
            existing.setDescricao(pacote.getDescricao());
            existing.setPreco(pacote.getPreco());
            existing.setDuracaoDias(pacote.getDuracaoDias());
            existing.setDestino(pacote.getDestino());
            return pacoteViagemRepository.save(existing);
        });
    }

    public boolean deletar(Long id) {
        if (pacoteViagemRepository.existsById(id)) {
            pacoteViagemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
