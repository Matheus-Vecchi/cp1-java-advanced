package model;

public class FuncionarioComMeta extends Funcionario {
    private int horasMinimasParaBonus;
    private double valorMeta;

    public FuncionarioComMeta(String nome, int horasTrabalhadas, double valorHora, int horasMinimasParaBonus, double valorMeta) {
        super(nome, horasTrabalhadas, valorHora);
        this.horasMinimasParaBonus = horasMinimasParaBonus;
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
        System.out.println("Cargo: Funcionário com meta");
        super.imprimirInformacao();
        System.out.println("Valor da meta R$" + valorMeta);
    }
}
