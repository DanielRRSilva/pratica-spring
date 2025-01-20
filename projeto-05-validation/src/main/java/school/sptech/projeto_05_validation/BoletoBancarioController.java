package school.sptech.projeto_05_validation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boletos-bancarios")
public class BoletoBancarioController {
    @Autowired
    private BoletoBancarioRepository boletoBancarioRepository;

    @PostMapping
    public ResponseEntity<BoletoBancario> cadastrar(@RequestBody @Valid BoletoBancario novoBoletoBancario) {
        novoBoletoBancario.setId(null);
        BoletoBancario resposta = boletoBancarioRepository.save(novoBoletoBancario);
        return ResponseEntity.status(201).body(resposta);
    }
}
