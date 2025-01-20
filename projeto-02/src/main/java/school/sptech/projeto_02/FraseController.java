package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frases")
public class FraseController {
    @GetMapping("/")
    public String ola() {
        return "Olá !";
    }

    @GetMapping("/bomdia")
    public String bomDia() {
        return "Bom dia !";
    }

    @GetMapping("/nome/{nome}")
    public String nome(@PathVariable String nome) {
        return "Bom dia " + nome + "!";
    }

    @GetMapping("/nome-completo/{nome}/{sobrenome}")
    public String nomeCompleto(@PathVariable String nome, @PathVariable String sobrenome) {
        return "Bem vindo, " + nome + " " + sobrenome;
    }
}
