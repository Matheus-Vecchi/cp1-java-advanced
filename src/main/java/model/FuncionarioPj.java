package model;

import annotations.Coluna;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PJ")
public class FuncionarioPj extends Funcionario {

    @Coluna(nome = "HORAS_ACORDADAS", nullable = true)
    @Column(name = "HORAS_ACORDADAS")
    private int horasAcordadas;

    public FuncionarioPj() {}

    public FuncionarioPj(String nome, int horasTrabalhadas, double valorHora, int horasAcordadas) {
        super(nome, horasTrabalhadas, valorHora);
        this.horasAcordadas = horasAcordadas;
    }

    @Override
    public double calcularSalario() {
        double salarioAcordado = horasAcordadas * getValorHora();

        if (getHorasTrabalhadas() <= horasAcordadas) {
            return salarioAcordado;
        } else {
            int horasExtras = getHorasTrabalhadas() - horasAcordadas;
            double valorHoraExtra = getValorHora() * 0.5;
            double totalHoraExtra = horasExtras * valorHoraExtra;
            return salarioAcordado + totalHoraExtra;
        }
    }

    @Override
    public void imprimirInformacao() {
        System.out.println("Cargo: PJ");
        super.imprimirInformacao();
        System.out.println("Horas acordadas no contrato: " + horasAcordadas);
    }

    public int getHorasAcordadas() { return horasAcordadas; }
    public void setHorasAcordadas(int horasAcordadas) { this.horasAcordadas = horasAcordadas; }
}
