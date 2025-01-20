package school.sptech.sprint1_nota1.ex3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExercicioDificilController {

    @GetMapping("/ex-03/{n}")
    public ExercicioDificilResponse exercicioDificil(@PathVariable int n) {
        ExercicioDificilResponse exercicioDificilResponse = new ExercicioDificilResponse();
        int termoAtual = 0;
        int proximoTermo = 1;
        int somaDosTermos;
        int soma = 0;
        if (n <= 0) {
            exercicioDificilResponse.setSoma(0);
            exercicioDificilResponse.setEnesimoTermo(0);
            return exercicioDificilResponse;
        }else {
            for (int i = 1; i <= n; i++) {
                soma += proximoTermo;
                somaDosTermos = termoAtual + proximoTermo;
                termoAtual = proximoTermo;
                proximoTermo = somaDosTermos;
            }
            exercicioDificilResponse.setEnesimoTermo(termoAtual);
            exercicioDificilResponse.setSoma(soma);
            return exercicioDificilResponse;
        }
    }
}
