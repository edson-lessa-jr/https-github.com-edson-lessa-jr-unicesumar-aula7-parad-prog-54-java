package br.unicesumar.sistema.modelo;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private int clienteId;
    private final List<Integer> produtoIds = new ArrayList<>();
    private double total;
    private String data;

    public Venda(int id, int clienteId, String data) {
        this.id = id;
        this.clienteId = clienteId;
        this.data = data;
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public double getTotal() {
        return total;
    }

    public String getData() {
        return data;
    }

    public List<Integer> getProdutoIds() {
        return new ArrayList<>(produtoIds);
    }

    public void adicionarProduto(int produtoId, double precoProduto) {
        this.produtoIds.add(produtoId);
        this.total += precoProduto;
    }

    public void setId(int id) {
        this.id = id;
    }
}
