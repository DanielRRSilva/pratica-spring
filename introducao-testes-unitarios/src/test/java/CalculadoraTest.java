import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Testes para classe da Calculadora.")
class CalculadoraTest {

    @Test
    @DisplayName("Dado que, passei números corretamente, retorna o resultado correto.")
    public void somarCorretamente() {
            Calculadora calculadora = new Calculadora();
            Double resultado = calculadora.somar(1.0, 2.0);
            assertEquals(3.0, resultado);
    }

    @Test
    @DisplayName("Dado que, passei números corretamente, retorna o resultado correto.")
    public void somarCorretamente2() {
        Calculadora calculadora = new Calculadora();
        Double resultado = calculadora.somar(2.0, 2.0);
        assertEquals(4.0, resultado);
    }

    @Test
    @DisplayName("Dado que, passei null, deve retornar uma exception.")
    public void somarNull() {
        Calculadora calculadora = new Calculadora();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->  calculadora.somar(null, 1.0));
        assertEquals("Não vale null", e.getMessage());
    }
}