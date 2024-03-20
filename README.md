# Sistema de Vendas

Este é um projeto de exemplo para um sistema de vendas desenvolvido em Spring Boot, que demonstra as operações CRUD (Create, Read, Update, Delete) em uma entidade `Cliente`.

## Funcionalidades

O projeto implementa as seguintes funcionalidades:

1. Salvar um novo cliente.
2. Atualizar os dados de um cliente existente.
3. Excluir um cliente.
4. Buscar todos os clientes.
5. Buscar clientes por nome.

## Pré-requisitos

- JDK (Java Development Kit) 11 ou superior
- Maven
- Spring Boot

## Configuração do Banco de Dados

Este projeto utiliza o banco de dados H2 em memória por padrão. Não é necessário configurar nada adicionalmente para o banco de dados.

## Operações CRUD

Para realizar as operações CRUD de forma programática sem o uso do repository, o arquivo `Clientes.java` no pacote `com.robson.vendas.domain.repositorio` contém todas as implementações necessárias.

### 1. Salvar um novo cliente

```java
public Cliente salvar(Cliente cliente) {
    entityManager.persist(cliente);
    return cliente;
}
```

### 2. Atualizar os dados de um cliente existente

```java
public Cliente atualizar(Cliente cliente) {
    entityManager.merge(cliente);
    return cliente;
}
```

### 3. Excluir um cliente

```java
public void deletar(Cliente cliente) {
    if (!entityManager.contains(cliente)) {
        cliente = entityManager.merge(cliente);
    }
    entityManager.remove(cliente);
}
```

### 4. Buscar todos os clientes

```java
public List<Cliente> obterTodos() {
    return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
}
```

### 5. Buscar clientes por nome

```java
public List<Cliente> buscarPorNome(String nome) {
    String jpql = "select c from Cliente c where c.nome like :nome";
    TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
    query.setParameter("nome", "%" + nome + "%");
    return query.getResultList();
}
```

---

Este README fornece uma visão geral do projeto e das operações CRUD implementadas sem o uso do repository. 
