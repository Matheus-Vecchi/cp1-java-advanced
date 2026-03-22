package app;

import dao.FuncionarioDAO;
import model.Funcionario;
import model.FuncionarioComMeta;
import model.FuncionarioPj;
import model.FuncionarioSenior;
import util.GeradorSQL;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Informações dos funcionários e cálculo de salários
        System.out.println("================== Informações dos Funcionários ================== ");

        Funcionario f1 = new Funcionario("Vampeta", 300, 400);
        Funcionario f2 = new FuncionarioSenior("Kleber Gladiador", 300, 400, 1200);
        Funcionario f3 = new FuncionarioComMeta("Neto", 300, 400, 300, 750000);
        Funcionario f4 = new FuncionarioPj("Marcão", 100, 400, 300);

        f1.imprimirInformacao();
        System.out.println("---------------");
        f2.imprimirInformacao();
        System.out.println("---------------");
        f3.imprimirInformacao();
        System.out.println("---------------");
        f4.imprimirInformacao();

        // Geração do SQl
        System.out.println("================== SQL Gerado via Reflection ================== ");


        System.out.println("\n-- SELECT - Funcionario base --");
        GeradorSQL.gerarSelect(f1);

        System.out.println("\n-- SELECT - FuncionarioSenior --");
        GeradorSQL.gerarSelect(f2);

        System.out.println("\n-- INSERT --");
        GeradorSQL.gerarInsert(f1);

        System.out.println("\n-- UPDATE --");
        GeradorSQL.gerarUpdate(f1, "ID = 1");

        System.out.println("\n-- DELETE --");
        GeradorSQL.gerarDelete(f1, "ID = 1");

        // CRUD COM BANCO ORACLE - JPA / Hibernate
        System.out.println("================== Crud no Banco de Dados ================== ");


        FuncionarioDAO dao = new FuncionarioDAO();

        // CREATE - persiste os funcionários
        Funcionario novo1 = new Funcionario("Vampeta", 300, 400);
        Funcionario novo2 = new FuncionarioSenior("Kleber Gladiador", 300, 400, 1200);
        Funcionario novo3 = new FuncionarioComMeta("Neto", 300, 400, 300, 750000);
        Funcionario novo4 = new FuncionarioPj("Marcão", 100, 400, 300);

        dao.salvar(novo1);
        dao.salvar(novo2);
        dao.salvar(novo3);
        dao.salvar(novo4);

        // READ ALL - lista todos os funcionários
        List<Funcionario> todos = dao.listarTodos(novo1);
        System.out.println("\nFuncionários cadastrados no banco:");
        for (Funcionario f : todos) {
            System.out.println("  ID: " + f.getId() + " | " + f.getNome() + " | Salário: R$" + f.calcularSalario());
        }

        // READ por ID - busca específica
        Funcionario encontrado = dao.buscarPorId(novo1.getId());
        if (encontrado != null) {
            System.out.println("\nBusca por ID - Funcionário encontrado:");
            encontrado.imprimirInformacao();
        }

        // UPDATE - altera o valor da hora do funcionário
        System.out.println("\nAlterando valor/hora de " + novo1.getNome() + " de R$400 para R$500...");
        novo1.setValorHora(500);
        dao.atualizar(novo1);

        // Verifica a atualização no banco
        Funcionario aposUpdate = dao.buscarPorId(novo1.getId());
        if (aposUpdate != null) {
            System.out.println("Novo salário após update: R$" + aposUpdate.calcularSalario());
        }

        // DELETE - remove o último funcionário inserido
        System.out.println("\nRemovendo funcionário PJ do banco...");
        dao.deletar(novo1.getId());

        // Lista final após o DELETE
        List<Funcionario> aposDelete = dao.listarTodos(novo1);
        System.out.println("\nFuncionários restantes após DELETE: " + aposDelete.size());
        for (Funcionario f : aposDelete) {
            System.out.println("  ID: " + f.getId() + " | " + f.getNome());
        }

        dao.fechar();
        System.out.println("================== Fim da Execução ================== ");
    }
}
