# Projeto Sistema de Vendas (C++)

## Informações Acadêmicas

* **Universidade:** Unicesumar
* **Módulo:** 54
* **Curso:** Paradigmas de Linguagem de Programação
* **Professor:** Edson Lessa
* **Tópico:** Aula 7 - Programação orientada a objetos: conceitos e modelagem

-----

## 1\. Visão Geral do Projeto

Este é um sistema simples desenvolvido em C++ para demonstrar os conceitos fundamentais da **Programação Orientada a Objetos (POO)**. O programa simula um pequeno sistema de vendas de console (terminal) onde é possível cadastrar pessoas (clientes), produtos e registrar vendas.

O objetivo principal não é ser um sistema comercial completo, mas sim servir como um exemplo prático de como modelar entidades do mundo real (como `Pessoa` e `Produto`) em classes e como organizar a lógica de negócios (como `VendaRepository`) em um código limpo e separado.

-----

## 2\. O Problema (Explicado de Forma Didática)

Imagine que você tem uma pequena loja. Você precisa manter um controle mínimo das suas operações. O que você precisa saber?

1.  **Quem são seus Clientes?** (Para saber para quem vendeu).
2.  **O que você Vende?** (Quais produtos sua loja oferece e seus preços).
3.  **Quais Vendas foram feitas?** (Registrar quem comprou o quê e qual foi o total).

Este projeto resolve esse problema criando um "modelo" digital da sua loja.

### Como o Programa Funciona?

Quando você inicia o programa, ele apresenta um menu principal. A partir dele, você pode navegar por três seções:

* **Gerenciar Pessoas:** Aqui, "Pessoa" é usado como sinônimo de "Cliente". Você pode:
    * **Criar:** Cadastrar um novo cliente (nome, e-mail).
    * **Listar:** Ver todos os clientes cadastrados.
    * **Atualizar:** Corrigir o nome ou e-mail de um cliente.
    * **Deletar:** Remover um cliente.
* **Gerenciar Produtos:** Aqui você cadastra os itens que sua loja vende. Você pode:
    * **Criar:** Cadastrar um novo produto (nome, preço).
    * **Listar:** Ver todos os produtos disponíveis.
    * *(Atualizar e Deletar não foram implementados neste menu, mas a base existe\!)*
* **Gerenciar Vendas:** Esta é a parte principal. Você pode:
    * **Criar Venda:** O sistema pergunta quem é o cliente (pelo ID) e, em seguida, permite que você adicione produtos (pelo ID) um por um. Ele calcula o total automaticamente.
    * **Listar Vendas:** Mostra um histórico de todas as vendas, quem foi o cliente e qual foi o valor total.

-----

## 3\. Conceitos de Programação Aplicados

Para alunos com menos experiência, a forma como o código está organizado é a parte mais importante.

### A. As "Entidades" (Os Moldes)

São as classes principais que representam os "dados" do nosso sistema. Elas estão na pasta `/include` (como `Pessoa.h`, `Produto.h`, `Venda.h`).

* `class Pessoa`: É o "molde" para criar clientes. Cada objeto `Pessoa` guarda seu próprio `id`, `nome` e `email`.
* `class Produto`: O "molde" para itens da loja. Cada objeto `Produto` guarda seu `id`, `nome` e `preco`.
* `class Venda`: O "molde" para um registro de venda. Guarda o `id` da venda, o `id` do cliente, uma lista de `ids` dos produtos vendidos e o `total` em dinheiro.

### B. Os "Repositórios" (Os Gaveteiros)

Se as classes acima são os "moldes", os Repositórios são os "gaveteiros" onde guardamos os objetos criados. Eles simulam um banco de dados, mas guardam tudo na memória (usando `std::map`).

* `class PessoaRepository`: É responsável por guardar, listar, encontrar e deletar objetos `Pessoa`.
* `class ProdutoRepository`: Faz o mesmo para `Produto`.
* `class VendaRepository`: Faz o mesmo para `Venda`.

**Por que isso é bom?** A lógica de *como* salvar (seja em memória, em um arquivo ou num banco de dados real) fica "escondida" dentro do repositório. O resto do programa não precisa saber, ele apenas pede: `repoPessoas.criar(novaPessoa)`.

### C. A "Interface" (O Atendimento)

São as classes que conversam com o usuário.

* `class Menu`: É responsável apenas por **mostrar as opções** na tela e **ler a escolha** do usuário (como em `exibirMenuPrincipal()`).
* `main.cpp`: É o "cérebro" que junta tudo. Ele:
    1.  Cria os "gaveteiros" (os Repositórios).
    2.  Mostra o menu principal (usando a classe `Menu`).
    3.  Chama as funções corretas (como `gerenciarPessoas` ou `gerenciarVendas`) com base na escolha do usuário.

Essa separação (Entidade vs. Repositório vs. Interface) é um dos conceitos mais importantes da Engenharia de Software\!

-----

## 4\. Estrutura dos Arquivos

```
/
├── include/        # "O que" as classes fazem (definições, .h)
│   ├── Menu.h
│   ├── Pessoa.h
│   ├── PessoaRepository.h
│   ├── Produto.h
│   ├── ProdutoRepository.h
│   ├── Venda.h
│   └── VendaRepository.h
│
├── src/            # "Como" as classes fazem (implementações, .cpp)
│   ├── Menu.cpp
│   ├── Pessoa.cpp
│   ├── PessoaRepository.cpp
│   ├── Produto.cpp
│   ├── ProdutoRepository.cpp
│   ├── Venda.cpp
│   ├── VendaRepository.cpp
│   └── main.cpp    # Onde tudo começa!
│
├── .gitignore      # Ignora arquivos de compilação
├── CMakeLists.txt  # Instruções para compilar com CMake
├── Makefile        # Instruções para compilar com Make
└── README.md       # Este arquivo
```

-----

## 5\. Como Compilar e Executar

Você pode usar um dos três métodos abaixo.

### Compilação (Make)

```bash
# Estando na pasta raiz do projeto
make
./bin/sistema
```

### Compilação (g++ direto)

```bash
# Estando na pasta raiz do projeto
# Cria o diretório 'bin' se ele não existir
mkdir -p bin
# Compila todos os .cpp, inclui os headers e gera o executável
g++ -std=c++17 -Iinclude src/*.cpp -o bin/sistema
./bin/sistema
```

### Compilação (CMake)

```bash
# Estando na pasta raiz do projeto
# 1. Configura o projeto (cria a pasta 'build')
cmake -S . -B build
# 2. Compila o projeto
cmake --build build --config Release
# 3. Executa
./build/sistema
```

-----

## 6\. Observações e Limitações

* **Banco de Dados em Memória:** Todos os dados (pessoas, produtos, vendas) são perdidos quando o programa fecha.
* **Total da Venda (FIXME):** Há uma observação no código (`src/VendaRepository.cpp`) e no `README.md` original. Ao *criar* uma venda, o total é calculado corretamente. No entanto, ao *listar* as vendas, o total pode aparecer zerado. Isso ocorre porque, ao salvar a venda, o repositório não está buscando os preços dos produtos novamente para recalcular o total.