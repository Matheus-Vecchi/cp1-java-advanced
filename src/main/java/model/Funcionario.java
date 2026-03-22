package model;

import annotations.Coluna;
import annotations.Descricao;
import jakarta.persistence.*;

@Descricao(descricao = "Tabela que armazena os dados dos funcionários")
@Entity
@Table(name = "TABELA_FUNCIONARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING, length = 20)
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario {

    @Coluna(nome = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "SEQ_FUNCIONARIO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Coluna(nome = "NOME", tamanho = 100, nullable = false)
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Coluna(nome = "HORAS_TRABALHADAS", nullable = false)
    @Column(name = "HORAS_TRABALHADAS", nullable = false)
    private int horasTrabalhadas;

    @Coluna(nome = "VALOR_HORA", nullable = false)
    @Column(name = "VALOR_HORA", nullable = false)
    private double valorHora;

    // Construtor padrão exigido pelo JPA
    public Funcionario() {}

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    public double calcularSalario() {
        return horasTrabalhadas * valorHora;
    }

    public void imprimirInformacao() {
        System.out.println("Nome: " + nome);
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.println("Valor da hora trabalhada: R$" + valorHora);
        System.out.println("Salário: R$" + calcularSalario());
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(int horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }
}
