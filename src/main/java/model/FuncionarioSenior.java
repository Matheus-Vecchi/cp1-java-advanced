package model;

public class FuncionarioSenior extends Funcionario {
    private static final int HORAS_BONUS = 15;
    private float valorBonus;

    public FuncionarioSenior(String nome, int horasTrabalhadas, float valorHora, float valorBonus) {
        super(nome, horasTrabalhadas, valorHora);
        this.valorBonus = valorBonus;
    }

    @Override
    public float calcularSalario() {
        int qtdBonus = getHorasTrabalhadas() / HORAS_BONUS;
        float totalBonus = qtdBonus * valorBonus;

        return super.calcularSalario() + totalBonus;
    }

    @Override
    public void imprimirInformacao() {
        super.imprimirInformacao();
        System.out.println("Valor do bônus: R$" + valorBonus);
    }
}
