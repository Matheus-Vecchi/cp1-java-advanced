package model;

public class FuncionarioComMeta extends Funcionario {
    private int horasMinimasParaBonus;
    private double valorMeta;

    public FuncionarioComMeta(String nome, int horasTrabalhadas, double valorHora, int minHoras, int valorMeta) {
        super(nome, horasTrabalhadas, valorHora);
        this.horasMinimasParaBonus = minHoras;
        this.valorMeta = valorMeta;
    }

    @Override
    public double calcularSalario() {
        if (getHorasTrabalhadas() >= horasMinimasParaBonus) {
            return super.calcularSalario() + valorMeta;
        } else {
            return super.calcularSalario();
        }
    }

    @Override
    public void imprimirInformacao() {
        super.imprimirInformacao();
        System.out.println("Valor da meta R$" + valorMeta);
    }
}
