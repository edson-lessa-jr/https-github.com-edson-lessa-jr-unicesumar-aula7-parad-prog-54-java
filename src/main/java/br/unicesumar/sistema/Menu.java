package br.unicesumar.sistema;

import java.util.Scanner;

public class Menu {

    private static final Scanner SC = new Scanner(System.in);

    private static int lerInteiro() {
        while (!SC.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor, digite um número: ");
            SC.next();
        }
        int v = SC.nextInt();
        SC.nextLine();
        return v;
    }

    public static int exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Vendas ---");
        System.out.println("1. Gerenciar Pessoas");
        System.out.println("2. Gerenciar Produtos");
        System.out.println("3. Gerenciar Vendas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public static int exibirMenuPessoas() {
        System.out.println("\n--- Gerenciar Pessoas ---");
        System.out.println("1. Criar Pessoa");
        System.out.println("2. Listar Pessoas");
        System.out.println("3. Atualizar Pessoa");
        System.out.println("4. Deletar Pessoa");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public static int exibirMenuProdutos() {
        System.out.println("\n--- Gerenciar Produtos ---");
        System.out.println("1. Criar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Deletar Produto");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public static int exibirMenuVendas() {
        System.out.println("\n--- Gerenciar Vendas ---");
        System.out.println("1. Criar Venda");
        System.out.println("2. Listar Vendas");
        System.out.println("3. Detalhar Venda (não implementado)");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        return lerInteiro();
    }
}
