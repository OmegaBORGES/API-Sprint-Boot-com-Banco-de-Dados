package com.agenciaviagens.api.controller;

import com.agenciaviagens.api.entity.AtividadeTuristica;
import com.agenciaviagens.api.service.AtividadeTuristicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeTuristicaController {

    @Autowired
    private AtividadeTuristicaService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeTuristica>> listarTodos() {
        return ResponseEntity.ok(atividadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeTuristica> buscarPorId(@PathVariable Long id) {
        return atividadeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeTuristica> criar(@Valid @RequestBody AtividadeTuristica atividade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.salvar(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeTuristica> atualizar(@PathVariable Long id, @Valid @RequestBody AtividadeTuristica atividade) {
        return atividadeService.atualizar(id, atividade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (atividadeService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
