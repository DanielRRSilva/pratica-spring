package school.sptech.sprint1_nota1.ex3;

public class ExercicioDificilResponse {
    private int enesimoTermo;
    private int soma;

    public ExercicioDificilResponse(int enesimoTermo, int soma) {
        this.enesimoTermo = enesimoTermo;
        this.soma = soma;
    }

    public ExercicioDificilResponse() {
    }

    public int getEnesimoTermo() {
        return enesimoTermo;
    }

    public void setEnesimoTermo(int enesimoTermo) {
        this.enesimoTermo = enesimoTermo;
    }

    public int getSoma() {
        return soma;
    }

    public void setSoma(int soma) {
        this.soma = soma;
    }

    @Override
    public String toString() {
        return "ExercicioDificilResponse{" +"enesimoTermo=" + enesimoTermo +", soma=" + soma +'}';
    }
}
