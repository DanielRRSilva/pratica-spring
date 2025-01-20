package com.example.projeto_http_code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/")
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros= livroRepository.findAll();
        if (livros.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> bucarPorId(@PathVariable Integer id) {
        Livro livroEncontrado = livroRepository.findById(id).orElse(null);
        if (livroEncontrado == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(livroEncontrado);
    }

    @GetMapping("/filtro-nome")
    public ResponseEntity<List<Livro>> porNome(@RequestParam String nome) {
        List<Livro> livroEncontrado = livroRepository.findByNome(nome);
        if (livroEncontrado.isEmpty())return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(livroEncontrado);
    }

    @PostMapping("/")
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro novoLivro) {
        novoLivro.setId(null);
        Livro livroRetorno = livroRepository.save(novoLivro);
        return ResponseEntity.status(201).body(livroRetorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Integer id, @RequestBody Livro livroAtualizado) {
        if (!livroRepository.existsById(id)) return ResponseEntity.status(404).build();
        livroAtualizado.setId(id);
        Livro livroRetorno = livroRepository.save(livroAtualizado);
        return ResponseEntity.status(200).body(livroRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
