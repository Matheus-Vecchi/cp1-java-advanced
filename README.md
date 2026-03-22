# 💼 Sistema de Funcionários em Java

## 📌 Descrição

Projeto desenvolvido em Java para a disciplina de **Java Advanced** da FIAP, representando diferentes perfis de funcionários com cálculo de salário personalizado.

O sistema aplica os seguintes conceitos:

- **Programação Orientada a Objetos** — encapsulamento, herança, polimorfismo e sobrescrita de métodos
- **Annotations customizadas** — `@Descricao` e `@Coluna` para mapeamento de entidades
- **API Reflection** — geração automática de código SQL a partir das annotations
- **JPA + Hibernate** — persistência de dados com `EntityManager`, `Persistence Unit` e `Persistence Context`
- **Banco Oracle** — CRUD completo (Create, Read, Update, Delete) com Oracle SQL Developer

---

## 🧱 Estrutura do Projeto

```
src/main/java/
├── annotations/
│   ├── Descricao.java        → @Descricao(descricao="...") — descreve a tabela no BD
│   └── Coluna.java           → @Coluna(nome, nullable, tamanho) — mapeia colunas
├── model/
│   ├── Funcionario.java      → classe base com @Entity e @Table(name="TABELA_FUNCIONARIO")
│   ├── FuncionarioSenior.java → bônus a cada 15 horas trabalhadas
│   ├── FuncionarioComMeta.java → bônus ao atingir meta de horas
│   └── FuncionarioPj.java    → contrato com horas acordadas + regra de hora extra
├── dao/
│   └── FuncionarioDAO.java   → CRUD com EntityManager (persist, find, merge, remove)
├── util/
│   └── GeradorSQL.java       → gera SELECT/INSERT/UPDATE/DELETE via Reflection
└── app/
    └── Main.java             → executa as 3 etapas: OOP → Reflection → CRUD Oracle

src/main/resources/META-INF/
└── persistence.xml           → configuração JPA/Hibernate com Oracle FIAP
```

---

## ⚙️ Funcionalidades

- Cálculo de salário com regras específicas por tipo de funcionário
- Exibição detalhada das informações de cada funcionário
- Geração automática de SQL via API Reflection lendo as annotations `@Descricao` e `@Coluna`
- CRUD completo com banco Oracle, exibindo o SQL executado em cada etapa
- Herança com `SINGLE_TABLE` — todos os tipos mapeados em uma única tabela com coluna discriminadora `TIPO`

---

## 🗄️ Configuração do Banco Oracle

Antes de executar, rode o script abaixo no **Oracle SQL Developer**:

```sql
CREATE SEQUENCE SEQ_FUNCIONARIO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
```

> A tabela `TABELA_FUNCIONARIO` é criada automaticamente pelo Hibernate na primeira execução (`hbm2ddl.auto = update`).

Em seguida, edite o arquivo `src/main/resources/META-INF/persistence.xml` com suas credenciais FIAP:

```xml
<property name="jakarta.persistence.jdbc.user"     value="SEU_RM"/>
<property name="jakarta.persistence.jdbc.password" value="SUA_SENHA"/>
```

---

## ▶️ Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/Matheus-Vecchi/cp1-java-advanced.git
```

2. Abra o projeto em uma IDE (IntelliJ, Eclipse ou NetBeans)

3. Certifique-se que as dependências Maven foram baixadas (`hibernate-core` e `ojdbc11`)

4. Configure suas credenciais no `persistence.xml`

5. Execute a sequence no Oracle SQL Developer

6. Execute a classe `Main`

---

## 📦 Dependências (pom.xml)

| Dependência | Versão |
|---|---|
| hibernate-core | 6.4.4.Final |
| ojdbc11 (Oracle) | 23.3.0.23.09 |
| Java | 17 |

---

## 👥 Integrantes

- Matheus Vecchi – RM561716
- Nicholas Buzo – RM561082

---

## 🔗 Repositório

https://github.com/Matheus-Vecchi/cp1-java-advanced

---

## 🚀 Status

✅ Concluído