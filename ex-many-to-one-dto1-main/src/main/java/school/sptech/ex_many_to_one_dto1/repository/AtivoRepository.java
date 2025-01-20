package school.sptech.ex_many_to_one_dto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;

import java.util.List;

// TODO: TERMINAR A CLASSE
@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {
    @Query("select a from Ativo a where lower(a.carteira.investidor) LIKE CONCAT('%',lower(:nome) ,'%') ")
    List<Ativo> findByInvestidorNome(String nome);

    @Query("select avg(a.valorAtual) from Ativo a where a.carteira.investidor LIKE :nome")
    Double findMediaValorByInvestidorNome(String nome);
}
