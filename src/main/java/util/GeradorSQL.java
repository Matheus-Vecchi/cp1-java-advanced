package util;

import annotations.Coluna;
import annotations.Descricao;
import jakarta.persistence.Table;

import java.lang.reflect.Field;

public class GeradorSQL {

    // Gera automaticamente SELECT * FROM <tabela> lendo as annotations via Reflection.
    //  Também imprime as colunas mapeadas com @Coluna e a descrição com @Descricao.
    public static String gerarSelect(Object objeto) {
        Class<?> clazz = objeto.getClass();
        String nomeTabela = resolverNomeTabela(clazz);

        System.out.println(">> Descrição: " + resolverDescricao(clazz));
        System.out.println(">> Colunas mapeadas via Reflection:");
        imprimirColunas(clazz);

        String sql = "SELECT * FROM " + nomeTabela;
        System.out.println(">> SQL gerado: " + sql);
        return sql;
    }

    public static String gerarInsert(Object objeto) {
        Class<?> clazz = objeto.getClass();
        String nomeTabela = resolverNomeTabela(clazz);

        StringBuilder colunas = new StringBuilder();
        StringBuilder valores = new StringBuilder();

        Class<?> c = clazz;
        while (c != null && c != Object.class) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Coluna.class)) {
                    Coluna col = field.getAnnotation(Coluna.class);
                    if (!colunas.isEmpty()) {
                        colunas.append(", ");
                        valores.append(", ");
                    }
                    colunas.append(col.nome());
                    valores.append("?");
                }
            }
            c = c.getSuperclass();
        }

        String sql = "INSERT INTO " + nomeTabela + " (" + colunas + ") VALUES (" + valores + ")";
        System.out.println(">> SQL gerado: " + sql);
        return sql;
    }

    public static String gerarUpdate(Object objeto, String condicao) {
        Class<?> clazz = objeto.getClass();
        String nomeTabela = resolverNomeTabela(clazz);

        StringBuilder set = new StringBuilder();
        Class<?> c = clazz;
        while (c != null && c != Object.class) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Coluna.class)) {
                    Coluna col = field.getAnnotation(Coluna.class);
                    if (!set.isEmpty()) set.append(", ");
                    set.append(col.nome()).append(" = ?");
                }
            }
            c = c.getSuperclass();
        }

        String sql = "UPDATE " + nomeTabela + " SET " + set + " WHERE " + condicao;
        System.out.println(">> SQL gerado: " + sql);
        return sql;
    }

    public static String gerarDelete(Object objeto, String condicao) {
        String nomeTabela = resolverNomeTabela(objeto.getClass());
        String sql = "DELETE FROM " + nomeTabela + " WHERE " + condicao;
        System.out.println(">> SQL gerado: " + sql);
        return sql;
    }

    // Helpers privados
    private static String resolverNomeTabela(Class<?> clazz) {
        Class<?> c = clazz;
        while (c != null) {
            if (c.isAnnotationPresent(Table.class)) {
                return c.getAnnotation(Table.class).name();
            }
            c = c.getSuperclass();
        }
        return "TAB_DESCONHECIDA";
    }

    private static String resolverDescricao(Class<?> clazz) {
        Class<?> c = clazz;
        while (c != null) {
            if (c.isAnnotationPresent(Descricao.class)) {
                return c.getAnnotation(Descricao.class).descricao();
            }
            c = c.getSuperclass();
        }
        return "Sem descrição";
    }

    private static void imprimirColunas(Class<?> clazz) {
        Class<?> c = clazz;
        while (c != null && c != Object.class) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Coluna.class)) {
                    Coluna col = field.getAnnotation(Coluna.class);
                    System.out.printf("   Campo: %-22s | Coluna BD: %-22s | Nullable: %-5b | Tamanho: %d%n",
                            field.getName(), col.nome(), col.nullable(), col.tamanho());
                }
            }
            c = c.getSuperclass();
        }
    }
}
