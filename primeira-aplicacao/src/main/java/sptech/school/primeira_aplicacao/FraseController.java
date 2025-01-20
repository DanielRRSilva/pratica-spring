package sptech.school.primeira_aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frases")
public class FraseController {

    @GetMapping
    public String frase() {
        return "Olá";
    }

    @GetMapping("/boa")
    public String frase2() {
        return "Boa pa nois!";
    }

    @GetMapping("/fraseBoa")
    public String legal() {
        return "Bacana";
    }

    @GetMapping("/nome/{nomeDaPessoa}")
    public String retornaNome(@PathVariable String nomeDaPessoa) {
        return  "Seja bem vindo " + nomeDaPessoa + " !";
    }

    @GetMapping("/nome/{nomeDaPessoa}/{sobrenome}")
    public String retornaNome(@PathVariable String nomeDaPessoa, @PathVariable String sobrenome) {
        return  "Seja bem vindo " + nomeDaPessoa + " " + sobrenome + " !";
    }
}
