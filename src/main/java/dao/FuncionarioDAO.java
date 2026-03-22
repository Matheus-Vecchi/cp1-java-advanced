package dao;

import jakarta.persistence.*;
import model.Funcionario;
import util.GeradorSQL;

import java.util.List;

public class FuncionarioDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public FuncionarioDAO() {
        this.emf = Persistence.createEntityManagerFactory("cp1-pu");
        this.em = emf.createEntityManager();
    }

    // CREATE
    public void salvar(Funcionario f) {
        System.out.println("\n[CREATE] Inserindo funcionário: " + f.getNome());
        GeradorSQL.gerarInsert(f);

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();

        System.out.println("Funcionário salvo! ID: " + f.getId());
    }

    // READ - buscar por ID
    public Funcionario buscarPorId(Long id) {
        System.out.println("\n[READ] Buscando funcionário por ID: " + id);
        System.out.println(">> SQL gerado: SELECT * FROM TABELA_FUNCIONARIO WHERE ID = " + id);

        Funcionario f = em.find(Funcionario.class, id);
        if (f != null) {
            System.out.println("Encontrado: " + f.getNome());
        } else {
            System.out.println("Nenhum funcionário encontrado com ID: " + id);
        }
        return f;
    }

    // READ - listar todos
    public List<Funcionario> listarTodos(Funcionario exemplo) {
        System.out.println("\n[READ ALL] Listando todos os funcionários:");
        GeradorSQL.gerarSelect(exemplo);

        List<Funcionario> lista = em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
        System.out.println("Total encontrado: " + lista.size() + " funcionário(s)");
        return lista;
    }

    // UPDATE
    public void atualizar(Funcionario f) {
        System.out.println("\n[UPDATE] Atualizando funcionário ID: " + f.getId());
        GeradorSQL.gerarUpdate(f, "ID = " + f.getId());

        em.getTransaction().begin();
        em.merge(f);
        em.getTransaction().commit();

        System.out.println("Funcionário atualizado com sucesso!");
    }

    // DELETE
    public void deletar(Long id) {
        System.out.println("\n[DELETE] Removendo funcionário ID: " + id);

        em.getTransaction().begin();
        Funcionario f = em.find(Funcionario.class, id);
        if (f != null) {
            GeradorSQL.gerarDelete(f, "ID = " + id);
            em.remove(f);
            System.out.println("Funcionário '" + f.getNome() + "' removido com sucesso!");
        } else {
            System.out.println("Funcionário com ID " + id + " não encontrado para remoção.");
        }
        em.getTransaction().commit();
    }

    public void fechar() {
        if (em.isOpen()) em.close();
        if (emf.isOpen()) emf.close();
    }
}
