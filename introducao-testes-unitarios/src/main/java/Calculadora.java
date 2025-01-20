public class Calculadora {

    public Double somar(Double a, Double b) {
        if (a == null || b == null) throw new IllegalArgumentException("Não vale null");
        return a + b;
    }
}
