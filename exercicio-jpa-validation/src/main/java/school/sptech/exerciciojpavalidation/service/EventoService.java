package school.sptech.exerciciojpavalidation.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciojpavalidation.entity.Evento;
import school.sptech.exerciciojpavalidation.repository.EventoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento porId(Integer id) {
        return eventoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Evento criar(Evento novoEvento) {
        if (eventoRepository.existsByDataEvento(novoEvento.getDataEvento())) throw new ResponseStatusException(HttpStatus.CONFLICT);
        novoEvento.setId(null);
        return eventoRepository.save(novoEvento);
    }

    public void deletar(Integer id) {
        if (!eventoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        eventoRepository.deleteById(id);
    }

    public List<Evento> listarGratuitos() {
        return eventoRepository.findByGratuitoTrue();
    }

    public List<Evento> listarData(LocalDate data) {
        return eventoRepository.findByDataEventoOrDataPublicacao(data, data);
    }

    public List<Evento> listarPeriodo(LocalDate inicio, LocalDate fim) {
        if (fim.isBefore(inicio)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return eventoRepository.findByDataEventoBetween(inicio, fim);
    }

    public Evento atualizar(Integer id, @Valid Evento eventoAtualizado) {
        if (eventoRepository.existsById(id)) {
            Evento evento = eventoRepository.findById(id).orElse(null);
            if (evento != null && (evento.isCancelado() || evento.getDataEvento().isBefore(LocalDate.now()))) throw new ResponseStatusException(HttpStatusCode.valueOf(422));
            eventoAtualizado.setId(id);
            return eventoRepository.save(eventoAtualizado);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void cancelar(Integer id) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento != null) {
            if (evento.isCancelado() || evento.getDataEvento().isBefore(LocalDate.now())) throw new ResponseStatusException(HttpStatus.valueOf(422));
            evento.setCancelado(true);
            eventoRepository.save(evento);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
