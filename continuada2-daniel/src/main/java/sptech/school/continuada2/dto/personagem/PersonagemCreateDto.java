package sptech.school.continuada2.dto.personagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemCreateDto {
    //TODO: Implementar validações
    @NotBlank
    private String nome;
    @NotBlank
    private String ultimate;
    @NotBlank
    private String funcaoPrincipal;
    @Positive
    @NotNull
    private int regiaoId;
}
