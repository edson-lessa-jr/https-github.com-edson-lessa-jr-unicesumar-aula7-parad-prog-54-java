package br.unicesumar.sistema.repositorio;

import br.unicesumar.sistema.modelo.Produto;

import java.util.*;

public class ProdutoRepository {
    private final Map<Integer, Produto> produtosDB = new LinkedHashMap<>();
    private int proximoId = 1;

    public void criar(Produto produto) {
        int id = proximoId++;
        Produto novo = new Produto(id, produto.getNome(), produto.getPreco());
        produtosDB.put(id, novo);
        System.out.println("Produto '" + novo.getNome() + "' criado com ID " + id + ".");
    }

    public Produto ler(int id) {
        return produtosDB.get(id);
    }

    public void atualizar(Produto produto) {
        if (produtosDB.containsKey(produto.getId())) {
            produtosDB.put(produto.getId(), produto);
            System.out.println("Produto ID " + produto.getId() + " atualizado.");
        } else {
            System.out.println("Erro: Produto ID " + produto.getId() + " não encontrado.");
        }
    }

    public void deletar(int id) {
        if (produtosDB.remove(id) != null) {
            System.out.println("Produto ID " + id + " deletado.");
        } else {
            System.out.println("Erro: Produto ID " + id + " não encontrado.");
        }
    }

    public List<Produto> lerTodos() {
        return new ArrayList<>(produtosDB.values());
    }
}
