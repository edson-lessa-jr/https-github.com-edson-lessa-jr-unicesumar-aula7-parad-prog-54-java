package br.unicesumar.sistema.repositorio;

import br.unicesumar.sistema.modelo.Venda;

import java.util.*;

public class VendaRepository {
    private final Map<Integer, Venda> vendasDB = new LinkedHashMap<>();
    private int proximoId = 1;

    public void criar(Venda venda) {
        int id = proximoId++;
        Venda nova = new Venda(id, venda.getClienteId(), venda.getData());
        for (Integer pid : venda.getProdutoIds()) {
            nova.adicionarProduto(pid, 0.0);
        }
        nova.setId(id);
        vendasDB.put(id, nova);
        System.out.println("Venda ID " + id + " criada.");
    }

    public Venda ler(int id) {
        return vendasDB.get(id);
    }

    public void atualizar(Venda venda) {
        if (vendasDB.containsKey(venda.getId())) {
            vendasDB.put(venda.getId(), venda);
            System.out.println("Venda ID " + venda.getId() + " atualizada.");
        } else {
            System.out.println("Erro: Venda ID " + venda.getId() + " não encontrada.");
        }
    }

    public void deletar(int id) {
        if (vendasDB.remove(id) != null) {
            System.out.println("Venda ID " + id + " deletada.");
        } else {
            System.out.println("Erro: Venda ID " + id + " não encontrada.");
        }
    }

    public List<Venda> lerTodos() {
        return new ArrayList<>(vendasDB.values());
    }
}
