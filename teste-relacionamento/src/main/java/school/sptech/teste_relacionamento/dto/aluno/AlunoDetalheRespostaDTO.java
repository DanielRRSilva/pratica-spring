package school.sptech.teste_relacionamento.dto.aluno;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AlunoDetalheRespostaDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private CursoDTO curso;

    @Data
    @Builder
    public static class CursoDTO {
        private Integer id;
        private String nome;
        private String descricao;
        private Double preco;
        private String categoria;
    }
}
