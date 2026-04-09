import java.util.Scanner;

public class DeliverySystem {

    private static ListaPedidos<Cliente> listaClientes = new ListaPedidos<>();
    private static ListaPedidos<Produto> catalogoProdutos = new ListaPedidos<>();
    private static PedidosAtivos pedidosAtivos = new PedidosAtivos();
    private static FilaPedidos filaPreparo = new FilaPedidos();
    private static PilhaPedidos historicoEntregas = new PilhaPedidos();

    private static int contadorPedidos = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        catalogoProdutos.adicionar(new Produto("Hamburguer Artesanal", 35.0, "Lanches", 50));
        catalogoProdutos.adicionar(new Produto("Refrigerante 2L", 12.0, "Bebidas", 100));

        while (opcao != 10) {
            System.out.println("\n==================================================");
            System.out.println("           SISTEMA DE DELIVERY (MENU)             ");
            System.out.println("==================================================");
            System.out.println("[1] Cadastrar Cliente");
            System.out.println("[2] Listar Clientes");
            System.out.println("[3] Cadastrar Produto");
            System.out.println("[4] Criar Novo Pedido");
            System.out.println("[5] Adicionar Item ao Pedido");
            System.out.println("[6] Enviar Pedido para a Cozinha (Fila)");
            System.out.println("[7] Preparar Próximo Pedido (Cozinha -> Histórico)");
            System.out.println("[8] Exibir Histórico de Pedidos (Pilha)");
            System.out.println("[9] Navegar entre Pedidos Ativos (Lista Dupla)");
            System.out.println("[10] Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    Cliente novoCliente = new Cliente();
                    novoCliente.cadastrar(scanner);
                    listaClientes.adicionar(novoCliente);
                    break;
                case 2:
                    System.out.println("\n--- Clientes Cadastrados ---");
                    listaClientes.exibirTodos();
                    break;
                case 3:
                    System.out.print("Nome do Produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Preço: ");
                    double precoProduto = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Estoque: ");
                    int estoque = scanner.nextInt();
                    scanner.nextLine();
                    catalogoProdutos.adicionar(new Produto(nomeProduto, precoProduto, categoria, estoque));
                    System.out.println("Produto cadastrado com sucesso!");
                    break;
                case 4:
                    criarPedidoFluxo(scanner);
                    break;
                case 5:
                    System.out.print("Digite o ID do pedido para adicionar itens: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("ID inválido!");
                        scanner.nextLine();
                        break;
                    }
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Pedido pedidoAlvo = pedidosAtivos.buscarPorId(idBusca);
                    if (pedidoAlvo == null) {
                        System.out.println("Pedido #" + idBusca + " não encontrado nos ativos.");
                        break;
                    }

                    System.out.println("\n--- Produtos Disponíveis ---");
                    catalogoProdutos.exibirTodos();

                    System.out.print("Digite o nome do produto: ");
                    String nomeProd = scanner.nextLine();
                    Produto produtoEscolhido = catalogoProdutos.buscarPorNome(nomeProd);
                    if (produtoEscolhido == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Quantidade: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Quantidade inválida!");
                        scanner.nextLine();
                        break;
                    }
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    ItemPedido novoItem = new ItemPedido(produtoEscolhido, qtd);
                    pedidoAlvo.adicionarItem(novoItem);
                    pedidoAlvo.exibirResumo();


                    break;
                case 6:
                   enviarParaCozinhaFluxo(scanner);
                    break;
                case 7:
                    Pedido pedidoPronto = filaPreparo.desenfileirar();
                    if (pedidoPronto != null) {
                        pedidoPronto.status = "Finalizado/Entregue";
                        historicoEntregas.empilhar(pedidoPronto);
                    }
                    break;
                case 8:
                    historicoEntregas.exibirHistorico();
                    break;
                case 9:
                    pedidosAtivos.navegar(scanner);
                    break;
                case 10:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }


    private static void criarPedidoFluxo(Scanner scanner) {
        System.out.print("Digite o nome do cliente para o pedido: ");
        String nomeBusca = scanner.nextLine();

        Cliente clienteEncontrado = listaClientes.buscarPorNome(nomeBusca);
        if (clienteEncontrado == null) {
            System.out.println("Cliente não encontrado. Cadastre-o primeiro.");
            return;
        }

        Pedido novoPedido = new Pedido(contadorPedidos++, clienteEncontrado.getNome(), clienteEncontrado.getEndereco());
        pedidosAtivos.adicionar(novoPedido);
        System.out.println("Pedido #" + novoPedido.getIdPedido() + " criado e adicionado aos ativos!");
    }

    private static void enviarParaCozinhaFluxo(Scanner scanner) {
        System.out.print("Digite o ID do pedido que deseja enviar para a cozinha: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido!");
            scanner.nextLine();
            return;
        }
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedidoEnvio = pedidosAtivos.buscarPorId(idPedido);
        if (pedidoEnvio == null) {
            System.out.println("Pedido #" + idPedido + " não encontrado nos ativos.");
            return;
        }
        pedidosAtivos.removerPorId(idPedido);
        pedidoEnvio.finalizarPedido(filaPreparo);


    }
}