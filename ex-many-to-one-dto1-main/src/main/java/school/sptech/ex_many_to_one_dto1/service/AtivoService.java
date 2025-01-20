package school.sptech.ex_many_to_one_dto1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;
import school.sptech.ex_many_to_one_dto1.exception.NaoEncontradoException;
import school.sptech.ex_many_to_one_dto1.repository.AtivoRepository;

import java.util.List;

// TODO: TERMINAR A CLASSE
@Service
@RequiredArgsConstructor
public class AtivoService {

    private final AtivoRepository ativoRepository;
    private final CarteiraService carteiraService;

    public Ativo salvar(Ativo ativo, int carteiraId) {
        Carteira carteira =  carteiraService.buscarPorId(carteiraId);
        ativo.setCarteira(carteira);
        return ativoRepository.save(ativo);
    }

    public List<Ativo> buscarTodos() {
        return ativoRepository.findAll();
    }

    public Ativo buscarPorId(int id) {
        return ativoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Ativo de id: %d não encontrado".formatted(id)));
    }

    public void deletarPorId(int id) {
        if (!ativoRepository.existsById(id)) throw new NaoEncontradoException("Ativo de id: %d não encontrado".formatted(id));
        ativoRepository.deleteById(id);
    }

    public List<Ativo> buscarAtivosPorInvestidorNome(String nome) {
        return ativoRepository.findByInvestidorNome(nome);
    }

    public Double buscarMediaAtivosPorInvestidorNome(String nome) {
        Double meida = ativoRepository.findMediaValorByInvestidorNome(nome);
        if (meida == null) throw new NaoEncontradoException("Ativo do investidor: %s não encontrado".formatted(nome));
        return meida;
    }


}
