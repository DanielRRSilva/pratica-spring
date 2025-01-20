package school.sptech.teste_relacionamento.dto.curso;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoCriacaoRequisicaoDTO {
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
}
