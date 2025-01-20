package school.sptech.prova_ac1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario novoUsuario) {
        if (usuarioRepository.findByEmailOrCpf(novoUsuario.getEmail(), novoUsuario.getCpf()) != null) return ResponseEntity.status(409).build();
        novoUsuario.setId(null);
        Usuario usuarioResponse = usuarioRepository.save(novoUsuario);
        return  ResponseEntity.status(201).body(usuarioResponse);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        if (listaUsuarios.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId (@PathVariable Integer id) {
        Usuario usuarioResponse = usuarioRepository.findById(id).orElse(null);
        if (usuarioResponse == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(usuarioResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId (@PathVariable Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-data")
    public ResponseEntity<List<Usuario>> buscarDataNascimentoMaiorQue (@RequestParam LocalDate dataNascimento) {
        List<Usuario> listaUsuariosComDataMaior = usuarioRepository.findByDataNascimentoGreaterThan(dataNascimento);
        if (listaUsuariosComDataMaior.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaUsuariosComDataMaior);
    }

}
