package br.unicesumar.sistema;

import br.unicesumar.sistema.modelo.Pessoa;
import br.unicesumar.sistema.modelo.Produto;
import br.unicesumar.sistema.modelo.Venda;
import br.unicesumar.sistema.repositorio.PessoaRepository;
import br.unicesumar.sistema.repositorio.ProdutoRepository;
import br.unicesumar.sistema.repositorio.VendaRepository;

import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    private static void gerenciarPessoas(PessoaRepository repo) {
        int escolha = -1;
        while (escolha != 0) {
            escolha = Menu.exibirMenuPessoas();
            switch (escolha) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = SC.nextLine();
                    System.out.print("Email: ");
                    String email = SC.nextLine();
                    repo.criar(new Pessoa(0, nome, email));
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Pessoas ---");
                    for (Pessoa p : repo.lerTodos()) {
                        System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() + ", Email: " + p.getEmail());
                    }
                }
                case 3 -> {
                    System.out.print("ID da Pessoa para atualizar: ");
                    int id = Integer.parseInt(SC.nextLine());
                    Pessoa p = repo.ler(id);
                    if (p == null) {
                        System.out.println("Pessoa não encontrada.");
                        break;
                    }
                    System.out.print("Novo Nome (Atual: " + p.getNome() + "): ");
                    String nome = SC.nextLine();
                    System.out.print("Novo Email (Atual: " + p.getEmail() + "): ");
                    String email = SC.nextLine();
                    p.setNome(nome);
                    p.setEmail(email);
                    repo.atualizar(p);
                }
                case 4 -> {
                    System.out.print("ID da Pessoa para deletar: ");
                    int id = Integer.parseInt(SC.nextLine());
                    repo.deletar(id);
                }
            }
        }
    }

    private static void gerenciarProdutos(ProdutoRepository repo) {
        int escolha = -1;
        while (escolha != 0) {
            escolha = Menu.exibirMenuProdutos();
            switch (escolha) {
                case 1 -> {
                    System.out.print("Nome do Produto: ");
                    String nome = SC.nextLine();
                    System.out.print("Preço: ");
                    double preco = Double.parseDouble(SC.nextLine());
                    repo.criar(new Produto(0, nome, preco));
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Produtos ---");
                    for (Produto p : repo.lerTodos()) {
                        System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() + ", Preço: R$ " + p.getPreco());
                    }
                }
                case 3 -> {
                    System.out.print("ID do Produto para atualizar: ");
                    int id = Integer.parseInt(SC.nextLine());
                    Produto prod = repo.ler(id);
                    if (prod == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }
                    System.out.print("Novo Nome (Atual: " + prod.getNome() + "): ");
                    String nome = SC.nextLine();
                    System.out.print("Novo Preço (Atual: " + prod.getPreco() + "): ");
                    double preco = Double.parseDouble(SC.nextLine());
                    prod.setNome(nome);
                    prod.setPreco(preco);
                    repo.atualizar(prod);
                }
                case 4 -> {
                    System.out.print("ID do Produto para deletar: ");
                    int id = Integer.parseInt(SC.nextLine());
                    repo.deletar(id);
                }
            }
        }
    }

    private static void gerenciarVendas(VendaRepository repoVendas, PessoaRepository repoPessoas, ProdutoRepository repoProdutos) {
        int escolha = -1;
        while (escolha != 0) {
            escolha = Menu.exibirMenuVendas();
            switch (escolha) {
                case 1 -> {
                    System.out.print("ID do Cliente (Pessoa): ");
                    int clienteId = Integer.parseInt(SC.nextLine());
                    if (repoPessoas.ler(clienteId) == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    Venda nova = new Venda(0, clienteId, "12/11/2025");
                    while (true) {
                        System.out.print("ID do Produto (ou 0 para finalizar): ");
                        int produtoId = Integer.parseInt(SC.nextLine());
                        if (produtoId == 0) break;
                        Produto p = repoProdutos.ler(produtoId);
                        if (p != null) {
                            nova.adicionarProduto(p.getId(), p.getPreco());
                            System.out.println(p.getNome() + " adicionado.");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    }
                    repoVendas.criar(nova);
                    System.out.println("Venda finalizada. Total: R$ " + nova.getTotal());
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Vendas ---");
                    for (Venda v : repoVendas.lerTodos()) {
                        Pessoa c = repoPessoas.ler(v.getClienteId());
                        String nomeCliente = (c != null) ? c.getNome() : "N/A";
                        System.out.println("ID Venda: " + v.getId() + ", Cliente: " + nomeCliente +
                                " (ID: " + v.getClienteId() + "), Total: R$ " + v.getTotal());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PessoaRepository repoPessoas = new PessoaRepository();
        ProdutoRepository repoProdutos = new ProdutoRepository();
        VendaRepository repoVendas = new VendaRepository();

        int escolhaMenu = -1;
        while (escolhaMenu != 0) {
            escolhaMenu = Menu.exibirMenuPrincipal();
            switch (escolhaMenu) {
                case 1 -> gerenciarPessoas(repoPessoas);
                case 2 -> gerenciarProdutos(repoProdutos);
                case 3 -> gerenciarVendas(repoVendas, repoPessoas, repoProdutos);
                case 0 -> System.out.println("Saindo do sistema.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
