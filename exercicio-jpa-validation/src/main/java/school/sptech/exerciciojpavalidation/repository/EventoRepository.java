package school.sptech.exerciciojpavalidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exerciciojpavalidation.entity.Evento;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    boolean existsByDataEvento(LocalDate dataEvento);
    
    List<Evento> findByGratuitoTrue();

    List<Evento> findByDataEventoOrDataPublicacao(LocalDate data1, LocalDate data2);

    List<Evento> findByDataEventoBetween(LocalDate inicio, LocalDate fim);
}
