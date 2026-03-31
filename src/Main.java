import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        FilaPedidos filaCozinha = new FilaPedidos();
        Cliente clienteAtual = new Cliente();
        ListaPedidos<Produto> catalogoProdutos = new ListaPedidos<>();
        
        Pedido pedidoEdicao = null;
        int contadorIdPedido = 1;
        int opcao;

        do {
            System.out.println("\n-=-=-=-=-= MENU DO SISTEMA -=-=-=-=-=");
            System.out.println("1. Gerenciar Cliente (Cadastrar/Atualizar)");
            System.out.println("2. Cadastrar Novo Produto no Catálogo");
            System.out.println("3. Abrir Novo Pedido");
            System.out.println("4. Adicionar Item ao Pedido (Busca no Catálogo)");
            System.out.println("5. Remover Item do Pedido");
            System.out.println("6. Enviar para Cozinha (Fila)");
            System.out.println("7. Finalizar Próximo Pedido da Fila");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = leitor.nextInt();
            leitor.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    if (clienteAtual.getNome() == null) clienteAtual.cadastrar(leitor);
                    else clienteAtual.atualizarDados(leitor);
                    break;

                case 2:
                    System.out.println("-=- CADASTRO DE PRODUTO -=-");
                    System.out.print("Nome do Produto: ");
                    String nomeP = leitor.nextLine();
                    System.out.print("Preço: ");
                    double precoP = leitor.nextDouble();
                    leitor.nextLine(); // limpa buffer
                    System.out.print("Categoria: ");
                    String catP = leitor.nextLine();
                    System.out.print("Estoque Inicial: ");
                    int estP = leitor.nextInt();
                    
                    Produto novoProd = new Produto(nomeP, precoP, catP, estP);
                    catalogoProdutos.adicionar(novoProd);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 3:
                    if (clienteAtual.getNome() == null) {
                        System.out.println("Erro: Cadastre um cliente primeiro!");
                    } else {
                        pedidoEdicao = new Pedido(contadorIdPedido++, clienteAtual.getNome(), clienteAtual.getEndereco());
                        System.out.println("Pedido #" + (contadorIdPedido-1) + " iniciado!");
                    }
                    break;

                case 4:
                    if (pedidoEdicao != null) {
                        System.out.println("\n--- ITENS NO CATÁLOGO ---");
                        catalogoProdutos.exibirTodos();
                        System.out.print("Digite o nome do produto para adicionar: ");
                        String busca = leitor.nextLine();
                        
                        Produto selecionado = catalogoProdutos.buscarPorNome(busca);
                        
                        if (selecionado != null) {
                            System.out.print("Quantidade: ");
                            int qtd = leitor.nextInt();
                            ItemPedido novoItem = new ItemPedido(selecionado, qtd);
                            pedidoEdicao.adicionarItem(novoItem);
                        } else {
                            System.out.println("Produto não encontrado no catálogo!");
                        }
                    } else {
                        System.out.println("Erro: Abra um pedido primeiro.");
                    }
                    break;

                case 5:
                    if (pedidoEdicao != null) {
                        System.out.print("Nome do item para remover: ");
                        String remover = leitor.nextLine();
                        ItemPedido item = pedidoEdicao.getListaItens().buscarPorNome(remover);
                        if (item != null) pedidoEdicao.removerItem(item);
                    }
                    break;

                case 6:
                    if (pedidoEdicao != null) {
                        pedidoEdicao.finalizarPedido(filaCozinha);
                        pedidoEdicao = null; 
                    }
                    break;

                case 7:
                    filaCozinha.desenfileirar();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 0);
        leitor.close();
    }
}
