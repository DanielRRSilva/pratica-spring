package school.sptech.teste_relacionamento.dto.curso.mapper;

import school.sptech.teste_relacionamento.dto.curso.CursoCriacaoRequisicaoDTO;
import school.sptech.teste_relacionamento.dto.curso.CursoDetalheRespostaDTO;
import school.sptech.teste_relacionamento.dto.curso.CursoResumoRespostaDTO;
import school.sptech.teste_relacionamento.entity.Curso;

public class CursoMapper {
    public static CursoDetalheRespostaDTO toDetalheDto(Curso entity) {
        if (entity == null) return null;
        CursoDetalheRespostaDTO dto = CursoDetalheRespostaDTO.builder().
                id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .categoria(entity.getCategoria())
                .build();
        return dto;
    }

    public static CursoResumoRespostaDTO toResumoDto(Curso entity) {
        if (entity == null) return null;
        CursoResumoRespostaDTO dto = CursoResumoRespostaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .build();
        return dto;
    }

    public static Curso toCricaoEntity(CursoCriacaoRequisicaoDTO dto) {
        if (dto == null) return null;
        Curso curso = Curso.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .build();
        return curso;
    }
}
