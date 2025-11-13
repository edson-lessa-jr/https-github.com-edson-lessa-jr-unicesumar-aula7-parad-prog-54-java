# Projeto Sistema de Vendas (Java)

Conversão 1:1 do projeto C++ para Java, mantendo entidades, repositórios em memória e menus de console.

## Como executar

```bash
mvn -q -DskipTests package
mvn -q exec:java
```

> Requisitos: JDK 17+ e Maven.

## Estrutura
```
src/main/java/br/unicesumar/sistema/
  Main.java
  Menu.java
  Pessoa.java
  Produto.java
  Venda.java
  PessoaRepository.java
  ProdutoRepository.java
  VendaRepository.java
```
