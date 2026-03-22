package app;

import model.Funcionario;
import model.FuncionarioSenior;

public class Main {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("Vampeta", 300, 400);
        FuncionarioSenior f2 = new FuncionarioSenior("Kleber", 300, 400, 1200);

        f1.imprimirInformacao();
        f2.imprimirInformacao();
    }
}
