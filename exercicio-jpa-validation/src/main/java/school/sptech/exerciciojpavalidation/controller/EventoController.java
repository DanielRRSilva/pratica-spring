package school.sptech.exerciciojpavalidation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.service.EventoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        List<Evento> eventos = eventoService.listar();
        if (eventos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoService.porId(id));
    }

    @GetMapping("/gratuitos")
    public ResponseEntity<List<Evento>> listarPorGratuitos() {
        List<Evento> eventosGratuitos = eventoService.listarGratuitos();
        if (eventosGratuitos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(eventosGratuitos);
    }

    @GetMapping("/data")
    public ResponseEntity<List<Evento>> listarPorData(@RequestParam LocalDate ocorrencia) {
        List<Evento> eventosPorData = eventoService.listarData(ocorrencia);
        if (eventosPorData.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(eventosPorData);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Evento>> listarPorPeriodo(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        List<Evento> eventosPorPeriodo = eventoService.listarPeriodo(inicio, fim);
        if (eventosPorPeriodo.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(eventosPorPeriodo);
    }

    @PostMapping
    public ResponseEntity<Evento> criar(@RequestBody @Valid Evento novoEvento) {
        return ResponseEntity.created(null).body(eventoService.criar(novoEvento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody @Valid Evento eventoAtualizado) {
       Evento eventoResposta = eventoService.atualizar(id, eventoAtualizado);
       return ResponseEntity.ok(eventoResposta);
    }

    @PatchMapping("/{id}/cancelamento")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        eventoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
