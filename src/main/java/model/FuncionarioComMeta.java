package model;

import annotations.Coluna;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COM_META")
public class FuncionarioComMeta extends Funcionario {

    @Coluna(nome = "HORAS_MIN_BONUS", nullable = true)
    @Column(name = "HORAS_MIN_BONUS")
    private int horasMinimasParaBonus;

    @Coluna(nome = "VALOR_META", nullable = true)
    @Column(name = "VALOR_META")
    private double valorMeta;

    // Construtor padrão exigido pelo JPA
    public FuncionarioComMeta() {}

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

    public int getHorasMinimasParaBonus() { return horasMinimasParaBonus; }
    public void setHorasMinimasParaBonus(int horasMinimasParaBonus) { this.horasMinimasParaBonus = horasMinimasParaBonus; }

    public double getValorMeta() { return valorMeta; }
    public void setValorMeta(double valorMeta) { this.valorMeta = valorMeta; }
}
