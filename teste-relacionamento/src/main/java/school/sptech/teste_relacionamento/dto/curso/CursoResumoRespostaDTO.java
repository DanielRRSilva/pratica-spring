package school.sptech.teste_relacionamento.dto.curso;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoResumoRespostaDTO {
    private Integer id;
    private String nome;
    private Double preco;
}
