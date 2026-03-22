package model;

public class FuncionarioPj extends Funcionario {
    private int horasAcordadas;

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
}
