package erhard.olivier.calculadoras;
import android.util.Log;

public class Historico {
    //Criação dos metodos
    //Criação dos Getters and Setters

    private int id;
    private double valorA;
    private double valorB;
    private String operacao;
    private double resultado;
    private String dataHora;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public double getValorA() {
        return valorA;
    }

    public void setValorA(double valorA) {
        this.valorA = valorA;
    }

    public double getValorB() {
        return valorB;
    }

    public void setValorB(double valorB) {
        this.valorB = valorB;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
