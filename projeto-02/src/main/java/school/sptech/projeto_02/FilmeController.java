package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private List<Filme> filmes = new ArrayList<>(List.of(
            new Filme("Transformes", "Michael Bay"),
            new Filme("Deadpool","Ryan Reynolds")
    ));

    @GetMapping("/favorito")
    public Filme favorito() {
        return  new Filme("Titanic", "James Cameron");
    }

    @GetMapping
    public List<Filme> filmes() {
        return  filmes;
    }

    @GetMapping("/criar/{nome}/{diretor}")
    public Filme criar(@PathVariable String nome, @PathVariable String diretor) {
        Filme filmeNovo = new Filme(nome, diretor);
        filmes.add(filmeNovo);
        return filmeNovo;
    }

    @GetMapping("/{index}")
    public Filme buscarPorIndex(@PathVariable int index) {
        if (index >= filmes.size() || index < 0) {
            return null;
        }else {
            return filmes.get(index);
        }
    }

    @GetMapping("/atualizar/{nome}/{diretor}/{index}")
    public Filme atualizar(@PathVariable String nome, @PathVariable String diretor, @PathVariable int index) {
        if (index < 0 || index >= filmes.size()) {
            return null;
        }
        return filmes.set(index, new Filme(nome, diretor));
    }
}
