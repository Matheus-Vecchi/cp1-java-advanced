package model;

import annotations.Coluna;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SENIOR")
public class FuncionarioSenior extends Funcionario {

    private static final int horas_bonus = 15;

    @Coluna(nome = "VALOR_BONUS", nullable = true)
    @Column(name = "VALOR_BONUS")
    private double valorBonus;

    public FuncionarioSenior() {}

    public FuncionarioSenior(String nome, int horasTrabalhadas, double valorHora, double valorBonus) {
        super(nome, horasTrabalhadas, valorHora);
        this.valorBonus = valorBonus;
    }

    @Override
    public double calcularSalario() {
        int qtdBonus = getHorasTrabalhadas() / horas_bonus;
        double totalBonus = qtdBonus * valorBonus;
        return super.calcularSalario() + totalBonus;
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("Cargo: Senior");
        super.imprimirInformacao();
        System.out.println("Valor do bônus: R$" + valorBonus);
    }

    public double getValorBonus() { return valorBonus; }
    public void setValorBonus(double valorBonus) { this.valorBonus = valorBonus; }
}
