package com.example.projeto_http_code;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByNome(String nome);

    List<Livro> findByNomeContainsIgnoreCase(String nome);
}
