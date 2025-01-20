package school.sptech.teste_relacionamento.dto.aluno.mapper;

import school.sptech.teste_relacionamento.dto.aluno.AlunoCriacaoRequisicaoDTO;
import school.sptech.teste_relacionamento.dto.aluno.AlunoDetalheRespostaDTO;
import school.sptech.teste_relacionamento.entity.Aluno;
import school.sptech.teste_relacionamento.entity.Curso;

public class AlunoMapper {

    public static AlunoDetalheRespostaDTO toDetalheRespostaDto(Aluno entidade) {
        if (entidade == null) return null;

        Curso curso = entidade.getCurso();

        AlunoDetalheRespostaDTO.CursoDTO cursoDto= AlunoDetalheRespostaDTO.CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .categoria(curso.getCategoria())
                .descricao(curso.getDescricao())
                .preco(curso.getPreco())
                .build();


        return AlunoDetalheRespostaDTO.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .cpf(entidade.getCpf())
                .dataNascimento(entidade.getDataNascimento())
                .curso(cursoDto)
                .build();
    }

    public static  Aluno toEntidade(AlunoCriacaoRequisicaoDTO alunoDto) {
        if (alunoDto == null) return null;
        return Aluno.builder()
                .nome(alunoDto.getNome())
                .cpf(alunoDto.getCpf())
                .dataNascimento(alunoDto.getDataNascimento())
                .build();
    }

}
