package school.sptech.teste_relacionamento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.teste_relacionamento.dto.curso.CursoCriacaoRequisicaoDTO;
import school.sptech.teste_relacionamento.dto.curso.CursoDetalheRespostaDTO;
import school.sptech.teste_relacionamento.dto.curso.mapper.CursoMapper;
import school.sptech.teste_relacionamento.dto.curso.CursoResumoRespostaDTO;
import school.sptech.teste_relacionamento.entity.Curso;
import school.sptech.teste_relacionamento.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDetalheRespostaDTO> cadastro(
            @RequestBody CursoCriacaoRequisicaoDTO cursoCriacaoDto
    ) {
        Curso cursoSalvo = this.cursoService.cadastrar(CursoMapper.toCricaoEntity(cursoCriacaoDto));
        return ResponseEntity.status(201).body(CursoMapper.toDetalheDto(cursoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<CursoResumoRespostaDTO>> listagem() {
        List<Curso> listagem = cursoService.listar();
        if (listagem.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<CursoResumoRespostaDTO> listaDtos = listagem.stream().map(CursoMapper::toResumoDto).toList();
        return ResponseEntity.status(200).body(listaDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResumoRespostaDTO> buscarPorId(@PathVariable Integer id) {
        Curso curso = this.cursoService.buscarPorId(id);
        return ResponseEntity.status(200).body(CursoMapper.toResumoDto(curso));
    }
}
