package school.sptech.projeto_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculos")
public class CalculadoraController {

    private int contador;

    @GetMapping("/contar")
    public int contar() {
        return ++contador;
    }

    @GetMapping("/somar/{numero1}/{numero2}")
    public int somar(@PathVariable int numero1, @PathVariable int numero2) {
        return numero1 + numero2;
    }
}
