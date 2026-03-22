package model;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private float valorHora;

    public Funcionario(String nome, int horasTrabalhadas, float valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    public String getNome() {
        return nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public float getValorHora() {
        return valorHora;
    }

    public float calcularSalario() {
        return horasTrabalhadas * valorHora;
    }

    public void imprimirInformacao() {
        System.out.println("Nome: " + nome);
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.println("Valor da hora trabalhada: R$" + valorHora);
        System.out.println("Salário: R$" + calcularSalario());
    }
}
