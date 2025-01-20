package sptech.school.projeto_03_http_persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {

}
