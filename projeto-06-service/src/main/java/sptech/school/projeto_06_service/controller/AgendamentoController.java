package sptech.school.projeto_06_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projeto_06_service.entity.Agendamento;
import sptech.school.projeto_06_service.service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        List<Agendamento> agendamentos = agendamentoService.listar();
        if  (agendamentos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar (@RequestBody @Valid Agendamento novoAgendamento) {
        Agendamento agendamentoCriado = agendamentoService.criar(novoAgendamento);
        return ResponseEntity.created(null).body(agendamentoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Integer id, @RequestBody @Valid Agendamento agendamentoAtualizado) {
        return ResponseEntity.ok(agendamentoService.atualizar(id, agendamentoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
