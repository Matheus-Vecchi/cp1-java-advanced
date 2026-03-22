package app;

import model.Funcionario;
import model.FuncionarioComMeta;
import model.FuncionarioSenior;

public class Main {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("Vampeta", 300, 400);
        Funcionario f2 = new FuncionarioSenior("Kleber Gladiador", 300, 400, 1200);
        Funcionario f3 = new FuncionarioComMeta("Neto", 300, 400, 300, 750000);

        f1.imprimirInformacao();
        System.out.println("---------------");
        f2.imprimirInformacao();
        System.out.println("---------------");
        f3.imprimirInformacao();
    }
}
