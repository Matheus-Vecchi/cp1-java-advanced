package model;

public class FuncionarioSenior extends Funcionario {
    private static final int HORAS_BONUS = 15;
    private double valorBonus;

    public FuncionarioSenior(String nome, int horasTrabalhadas, double valorHora, double valorBonus) {
        super(nome, horasTrabalhadas, valorHora);
        this.valorBonus = valorBonus;
    }

    @Override
    public double calcularSalario() {
        int qtdBonus = getHorasTrabalhadas() / HORAS_BONUS;
        double totalBonus = qtdBonus * valorBonus;

        return super.calcularSalario() + totalBonus;
    }

    @Override
    public void imprimirInformacao() {
        super.imprimirInformacao();
        System.out.println("Valor do bônus: R$" + valorBonus);
    }
}
