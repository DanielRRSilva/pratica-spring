package school.sptech.ex_many_to_one_dto1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoMapper;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoRequestDto;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoResponseDto;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.service.AtivoService;

import java.util.List;

// TODO: TERMINAR A CLASSE
@RestController
@RequestMapping("/ativos")
@RequiredArgsConstructor
public class AtivoController {

    private final AtivoService ativoService;

    @PostMapping
    public ResponseEntity<AtivoResponseDto> cadastrar(@RequestBody @Valid AtivoRequestDto ativoRequestDto) {
        Ativo ativo = AtivoMapper.toAtivoEntity(ativoRequestDto);
        AtivoResponseDto ativoResponseDto = AtivoMapper.toAtivoResponseDto(ativoService.salvar(ativo,ativoRequestDto.getCarteiraId()));
        return ResponseEntity.created(null).body(ativoResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<AtivoResponseDto>> buscarTodos() {
        List<Ativo> ativos = ativoService.buscarTodos();
        if (ativos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ativos.stream().map(AtivoMapper::toAtivoResponseDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivoResponseDto> buscarPorId(@PathVariable Integer id) {
        Ativo ativo = ativoService.buscarPorId(id);
        return ResponseEntity.ok(AtivoMapper.toAtivoResponseDto(ativo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        ativoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carteiras")
    public ResponseEntity<List<AtivoResponseDto>> buscarAtivosPorCarteiraNome(@RequestParam String nomeInvestidor) {
        List<Ativo> ativos = ativoService.buscarAtivosPorInvestidorNome(nomeInvestidor);
        if (ativos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ativos.stream().map(AtivoMapper::toAtivoResponseDto).toList());
    }

    @GetMapping("/carteiras/media")
    public ResponseEntity<Double> buscarMediaAtivosPorCarteiraNome(@RequestParam String nomeInvestidor) {
        return ResponseEntity.ok(ativoService.buscarMediaAtivosPorInvestidorNome(nomeInvestidor));
    }
}
