package com.agenciaviagens.api.service;

import com.agenciaviagens.api.entity.Hotel;
import com.agenciaviagens.api.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> listarTodos() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> buscarPorId(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel salvar(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> atualizar(Long id, Hotel hotel) {
        return hotelRepository.findById(id).map(existing -> {
            existing.setNome(hotel.getNome());
            existing.setEndereco(hotel.getEndereco());
            existing.setCategoria(hotel.getCategoria());
            existing.setDisponibilidade(hotel.getDisponibilidade());
            existing.setDestino(hotel.getDestino());
            return hotelRepository.save(existing);
        });
    }

    public boolean deletar(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
