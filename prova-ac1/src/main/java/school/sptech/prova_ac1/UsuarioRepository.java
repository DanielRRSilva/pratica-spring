package school.sptech.prova_ac1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailOrCpf(String email, String cpf);
    List<Usuario> findByDataNascimentoGreaterThan(LocalDate dataNascimento);
}
