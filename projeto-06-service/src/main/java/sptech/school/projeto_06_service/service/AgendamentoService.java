package sptech.school.projeto_06_service.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.projeto_06_service.entity.Agendamento;
import sptech.school.projeto_06_service.exception.EntidadeNaoEncontradaException;
import sptech.school.projeto_06_service.repository.AgendamentoRepository;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar() {
        return  agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Integer id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Agendamento"));
//        return agendamentoRepository.findById(id).orElseThrow(EntidadeNaoEncontradaException::new);
    }

    public Agendamento criar(Agendamento novoAgendamento) {
        if (agendamentoRepository.existsByData(novoAgendamento.getData())) throw new ResponseStatusException(HttpStatus.CONFLICT);
        novoAgendamento.setId(null);
        return agendamentoRepository.save(novoAgendamento);
    }

    public Agendamento atualizar(Integer id, Agendamento agendamentoAtualizado) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoAtualizado.setId(id);
            agendamentoRepository.save(agendamentoAtualizado);
        }
        throw new EntidadeNaoEncontradaException("Agendamento");
    }

    public void deletar(Integer id) {
        if (!agendamentoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("Agendamento");
        agendamentoRepository.deleteById(id);
    }
}
