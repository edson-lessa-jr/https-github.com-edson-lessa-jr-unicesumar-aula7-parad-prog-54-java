package br.unicesumar.sistema.repositorio;

import br.unicesumar.sistema.modelo.Pessoa;

import java.util.*;

public class PessoaRepository {
    private final Map<Integer, Pessoa> pessoasDB = new LinkedHashMap<>();
    private int proximoId = 1;

    public void criar(Pessoa pessoa) {
        int id = proximoId++;
        Pessoa nova = new Pessoa(id, pessoa.getNome(), pessoa.getEmail());
        pessoasDB.put(id, nova);
        System.out.println("Pessoa '" + nova.getNome() + "' criada com ID " + id + ".");
    }

    public Pessoa ler(int id) {
        return pessoasDB.get(id);
    }

    public void atualizar(Pessoa pessoa) {
        if (pessoasDB.containsKey(pessoa.getId())) {
            pessoasDB.put(pessoa.getId(), pessoa);
            System.out.println("Pessoa ID " + pessoa.getId() + " atualizada.");
        } else {
            System.out.println("Erro: Pessoa ID " + pessoa.getId() + " não encontrada.");
        }
    }

    public void deletar(int id) {
        if (pessoasDB.remove(id) != null) {
            System.out.println("Pessoa ID " + id + " deletada.");
        } else {
            System.out.println("Erro: Pessoa ID " + id + " não encontrada.");
        }
    }

    public List<Pessoa> lerTodos() {
        return new ArrayList<>(pessoasDB.values());
    }
}
