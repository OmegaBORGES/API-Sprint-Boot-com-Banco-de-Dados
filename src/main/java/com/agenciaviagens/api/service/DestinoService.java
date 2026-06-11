package com.agenciaviagens.api.service;

import com.agenciaviagens.api.entity.Destino;
import com.agenciaviagens.api.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }

    public Optional<Destino> buscarPorId(Long id) {
        return destinoRepository.findById(id);
    }

    public Destino salvar(Destino destino) {
        return destinoRepository.save(destino);
    }

    public Optional<Destino> atualizar(Long id, Destino destino) {
        return destinoRepository.findById(id).map(existing -> {
            existing.setNome(destino.getNome());
            existing.setDescricao(destino.getDescricao());
            existing.setPais(destino.getPais());
            existing.setClima(destino.getClima());
            return destinoRepository.save(existing);
        });
    }

    public boolean deletar(Long id) {
        if (destinoRepository.existsById(id)) {
            destinoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
