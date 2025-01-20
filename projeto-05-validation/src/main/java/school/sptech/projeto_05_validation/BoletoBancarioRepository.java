package school.sptech.projeto_05_validation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoletoBancarioRepository extends JpaRepository<BoletoBancario, Integer> {
    @Query("SELECT b FROM BoletoBancario b WHERE b.nomePagador = :nome and b.usuario.nome = :nome")
    List<BoletoBancario> buscarBoletosPorNome(String nome);
}
