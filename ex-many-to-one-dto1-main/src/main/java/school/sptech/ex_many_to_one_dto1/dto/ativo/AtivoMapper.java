package school.sptech.ex_many_to_one_dto1.dto.ativo;

import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;

// TODO: TERMINAR A CLASSE
public class AtivoMapper {
    public static AtivoResponseDto toAtivoResponseDto(Ativo ativo) {
        if (ativo == null) return null;

        Carteira carteira = ativo.getCarteira();

        AtivoResponseDto.AtivoCarteiraResponseDto carteiraResponseDto = AtivoResponseDto.AtivoCarteiraResponseDto.builder()
                .id(carteira.getId())
                .nome(carteira.getNome())
                .investidor(carteira.getInvestidor())
                .build();

        return AtivoResponseDto.builder()
                .id(ativo.getId())
                .nome(ativo.getNome())
                .tipo(ativo.getTipo())
                .valorAtual(ativo.getValorAtual())
                .carteira(carteiraResponseDto)
                .build();
    }

    public static Ativo toAtivoEntity(AtivoRequestDto ativoRequestDto) {
        if (ativoRequestDto == null) return null;
        return Ativo.builder()
                .nome(ativoRequestDto.getNome())
                .tipo(ativoRequestDto.getTipo())
                .valorAtual(ativoRequestDto.getValorAtual())
                .build();
    }
}
