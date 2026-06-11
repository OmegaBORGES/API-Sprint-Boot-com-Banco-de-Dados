package com.agenciaviagens.api.controller;

import com.agenciaviagens.api.entity.PacoteViagem;
import com.agenciaviagens.api.service.PacoteViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacotes")
public class PacoteViagemController {

    @Autowired
    private PacoteViagemService pacoteViagemService;

    @GetMapping
    public ResponseEntity<List<PacoteViagem>> listarTodos() {
        return ResponseEntity.ok(pacoteViagemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacoteViagem> buscarPorId(@PathVariable Long id) {
        return pacoteViagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacoteViagem> criar(@Valid @RequestBody PacoteViagem pacote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacoteViagemService.salvar(pacote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacoteViagem> atualizar(@PathVariable Long id, @Valid @RequestBody PacoteViagem pacote) {
        return pacoteViagemService.atualizar(id, pacote)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (pacoteViagemService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
