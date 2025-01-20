package sptech.school.projeto_03_http_persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private List<Musica> musicas = new ArrayList<>();

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping("/listar")
    public List<Musica> listar() {
        return musicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Musica buscaPorIndex(@PathVariable Integer id) {
        return musicaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Musica criar(@RequestBody Musica novaMusica) {
        return musicaRepository.save(novaMusica);
    }

    @PutMapping("/{id}")
    public Musica atualizar(@PathVariable int id, @RequestBody Musica musicaAtualizada) {
       if (musicaRepository.existsById(id)) {
           musicaAtualizada.setId(id);
           return musicaRepository.save(musicaAtualizada);
       }
        return musicaAtualizada;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        if (musicaRepository.existsById(id))
            musicaRepository.deleteById(id);
    }
}
