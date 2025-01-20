package school.sptech.teste_relacionamento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.teste_relacionamento.dto.aluno.AlunoCriacaoRequisicaoDTO;
import school.sptech.teste_relacionamento.dto.aluno.AlunoDetalheRespostaDTO;
import school.sptech.teste_relacionamento.dto.aluno.mapper.AlunoMapper;
import school.sptech.teste_relacionamento.entity.Aluno;
import school.sptech.teste_relacionamento.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDetalheRespostaDTO>> listar() {
        List<Aluno> listaAlunos = alunoService.listar();
        if (listaAlunos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaAlunos.stream().map(AlunoMapper::toDetalheRespostaDto).toList());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<AlunoDetalheRespostaDTO> porId(@PathVariable int id) {
        return ResponseEntity.ok(AlunoMapper.toDetalheRespostaDto(alunoService.porId(id)));
    }

    @PostMapping
    public  ResponseEntity<AlunoDetalheRespostaDTO> cadastrar(@Valid @RequestBody AlunoCriacaoRequisicaoDTO criacaoDto) {
        Aluno alunoNovo = alunoService.criar(AlunoMapper.toEntidade(criacaoDto), criacaoDto.getCursoId());
        return ResponseEntity.created(null).body(AlunoMapper.toDetalheRespostaDto(alunoNovo));
    }
}
