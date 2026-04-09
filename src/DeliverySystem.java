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

        while (opcao != 17) {
            System.out.println("\n==================================================");
            System.out.println("           SISTEMA DE DELIVERY (MENU)             ");
            System.out.println("==================================================");
            System.out.println("--- CLIENTES ---");
            System.out.println("[1]  Cadastrar Cliente");
            System.out.println("[2]  Listar Clientes");
            System.out.println("[3]  Atualizar Dados do Cliente");
            System.out.println("[4]  Exibir Ficha do Cliente");
            System.out.println("--- PRODUTOS ---");
            System.out.println("[5]  Cadastrar Produto");
            System.out.println("[6]  Listar Produtos");
            System.out.println("--- PEDIDOS ---");
            System.out.println("[7]  Criar Novo Pedido");
            System.out.println("[8]  Adicionar Item ao Pedido");
            System.out.println("[9]  Remover Item do Pedido");
            System.out.println("[10] Exibir Total do Pedido");
            System.out.println("[11] Enviar Pedido para a Cozinha (Fila)");
            System.out.println("--- COZINHA ---");
            System.out.println("[12] Exibir Pedidos em Preparo");
            System.out.println("[13] Ver Proximo Pedido da Fila");
            System.out.println("[14] Finalizar Pedido (Cozinha -> Historico)");
            System.out.println("--- HISTORICO / NAVEGACAO ---");
            System.out.println("[15] Exibir Historico de Pedidos (Pilha)");
            System.out.println("[16] Navegar entre Pedidos Ativos (Lista Dupla)");
            System.out.println("[17] Sair");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcao = 0;
            }

            switch (opcao) {

                // ── CLIENTES ──────────────────────────────────────────
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
                    // usa atualizarDados() de Cliente
                    atualizarClienteFluxo(scanner);
                    break;

                case 4:
                    // usa exibirDados() de Cliente
                    exibirFichaClienteFluxo(scanner);
                    break;

                // ── PRODUTOS ──────────────────────────────────────────
                case 5:
                    System.out.println("\n--- Produtos ja cadastrados ---");
                    catalogoProdutos.exibirTodos();
                    System.out.print("Nome do Produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Preco: ");
                    double precoProduto = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Estoque: ");
                    int estoque = scanner.nextInt();
                    scanner.nextLine();
                    Produto novoProduto = new Produto(nomeProduto, precoProduto, categoria, estoque);
                    catalogoProdutos.adicionar(novoProduto);
                    System.out.println("Produto cadastrado com sucesso!");
                    // usa exibirProduto() de Produto para confirmar o cadastro
                    novoProduto.exibirProduto();
                    break;

                case 6:
                    System.out.println("\n--- Catalogo de Produtos ---");
                    catalogoProdutos.exibirTodos();
                    break;

                // ── PEDIDOS ───────────────────────────────────────────
                case 7:
                    criarPedidoFluxo(scanner);
                    break;

                case 8:
                    adicionarItemFluxo(scanner);
                    break;

                case 9:
                    // usa removerItem() de Pedido
                    removerItemFluxo(scanner);
                    break;

                case 10:
                    // usa calcularTotal() de Pedido
                    exibirTotalFluxo(scanner);
                    break;

                case 11:
                    enviarParaCozinhaFluxo(scanner);
                    break;

                // ── COZINHA ───────────────────────────────────────────
                case 12:
                    filaPreparo.exibirFila();
                    break;

                case 13:
                    filaPreparo.exibirProximo();
                    break;

                case 14:
                    Pedido pedidoPronto = filaPreparo.desenfileirar();
                    if (pedidoPronto != null) {
                        pedidoPronto.status = "Finalizado/Entregue";
                        historicoEntregas.empilhar(pedidoPronto);
                        System.out.println("Pedido #" + pedidoPronto.getIdPedido() + " finalizado!");
                        // usa getTamanho() de PilhaPedidos
                        System.out.println("Total no historico: " + historicoEntregas.getTamanho());
                    }
                    break;

                // ── HISTORICO / NAVEGACAO ─────────────────────────────
                case 15:
                    historicoEntregas.exibirHistorico();
                    // usa getTamanho() de PilhaPedidos
                    System.out.println("Total de pedidos finalizados: " + historicoEntregas.getTamanho());
                    break;

                case 16:
                    // usa getTamanho() de PedidosAtivos antes de navegar
                    System.out.println("Pedidos ativos no momento: " + pedidosAtivos.getTamanho());
                    pedidosAtivos.navegar(scanner);
                    break;

                case 17:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        scanner.close();
    }

    // ── CLIENTES ──────────────────────────────────────────────────────

    // usa atualizarDados() de Cliente
    private static void atualizarClienteFluxo(Scanner scanner) {
        System.out.println("\n--- Clientes Cadastrados ---");
        listaClientes.exibirTodos();
        System.out.print("Digite o nome do cliente para atualizar: ");
        String nomeBusca = scanner.nextLine();
        Cliente cliente = listaClientes.buscarPorNome(nomeBusca);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        cliente.atualizarDados(scanner);
    }

    // usa exibirDados() de Cliente
    private static void exibirFichaClienteFluxo(Scanner scanner) {
        System.out.println("\n--- Clientes Cadastrados ---");
        listaClientes.exibirTodos();
        System.out.print("Digite o nome do cliente para exibir a ficha: ");
        String nomeBusca = scanner.nextLine();
        Cliente cliente = listaClientes.buscarPorNome(nomeBusca);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        cliente.exibirDados();
    }

    // ── PEDIDOS ───────────────────────────────────────────────────────

    private static void criarPedidoFluxo(Scanner scanner) {
        System.out.println("\n--- Clientes Cadastrados ---");
        listaClientes.exibirTodos();
        System.out.print("Digite o nome do cliente para o pedido: ");
        String nomeBusca = scanner.nextLine();
        Cliente clienteEncontrado = listaClientes.buscarPorNome(nomeBusca);
        if (clienteEncontrado == null) {
            System.out.println("Cliente nao encontrado. Cadastre-o primeiro.");
            return;
        }
        Pedido novoPedido = new Pedido(contadorPedidos++, clienteEncontrado.getNome(), clienteEncontrado.getEndereco());
        pedidosAtivos.adicionar(novoPedido);
        System.out.println("Pedido #" + novoPedido.getIdPedido() + " criado para " + clienteEncontrado.getNome() + "!");
        // usa getTamanho() de PedidosAtivos
        System.out.println("Pedidos ativos no momento: " + pedidosAtivos.getTamanho());
    }

    private static void adicionarItemFluxo(Scanner scanner) {
        pedidosAtivos.exibirTodos();
        System.out.print("Digite o ID do pedido: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidosAtivos.buscarPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido #" + idPedido + " nao encontrado nos ativos.");
            return;
        }

        System.out.println("\n--- Produtos Disponiveis ---");
        catalogoProdutos.exibirTodos();
        System.out.print("Digite o ID do produto: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        Produto produto = catalogoProdutos.buscarPorCondicao(p -> p.idProduto == idProduto);
        if (produto == null) { System.out.println("Produto nao encontrado."); return; }

        System.out.print("Quantidade: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("Quantidade invalida!"); return; }
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) { System.out.println("Quantidade deve ser maior que zero."); return; }
        if (produto.estoque < quantidade) {
            System.out.println("Estoque insuficiente! Disponivel: " + produto.estoque);
            return;
        }

        produto.atualizarEstoque(-quantidade);
        pedido.adicionarItem(new ItemPedido(produto, quantidade));
        pedido.exibirResumo();
    }

    // usa removerItem() de Pedido — com devolucao ao estoque
    private static void removerItemFluxo(Scanner scanner) {
        pedidosAtivos.exibirTodos();
        System.out.print("Digite o ID do pedido: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidosAtivos.buscarPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido #" + idPedido + " nao encontrado nos ativos.");
            return;
        }
        if (pedido.getListaItens().estaVazia()) {
            System.out.println("Este pedido nao possui itens.");
            return;
        }

        pedido.exibirResumo();
        System.out.println("\n--- Produtos no Catalogo ---");
        catalogoProdutos.exibirTodos();
        System.out.print("Digite o ID do produto a remover: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        // busca o ItemPedido dentro da lista do pedido pelo ID do produto
        ItemPedido itemParaRemover = pedido.getListaItens().buscarPorCondicao(
                item -> item.produto.idProduto == idProduto
        );

        if (itemParaRemover == null) {
            System.out.println("Produto nao encontrado nos itens deste pedido.");
            return;
        }

        // devolve a quantidade ao estoque antes de remover
        itemParaRemover.produto.atualizarEstoque(itemParaRemover.quantidade);
        pedido.removerItem(itemParaRemover);
        pedido.exibirResumo();
    }

    // usa calcularTotal() de Pedido
    private static void exibirTotalFluxo(Scanner scanner) {
        pedidosAtivos.exibirTodos();
        System.out.print("Digite o ID do pedido: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidosAtivos.buscarPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido #" + idPedido + " nao encontrado nos ativos.");
            return;
        }
        System.out.printf("Total atual do Pedido #%d: R$ %.2f%n", pedido.getIdPedido(), pedido.calcularTotal());
    }

    private static void enviarParaCozinhaFluxo(Scanner scanner) {
        pedidosAtivos.exibirTodos();
        System.out.print("Digite o ID do pedido que deseja enviar para a cozinha: ");
        if (!scanner.hasNextInt()) { scanner.nextLine(); System.out.println("ID invalido!"); return; }
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidosAtivos.buscarPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido #" + idPedido + " nao encontrado nos ativos.");
            return;
        }
        if (pedido.getListaItens().estaVazia()) {
            System.out.println("Nao e possivel enviar um pedido sem itens!");
            return;
        }
        pedidosAtivos.removerPorId(idPedido);
        pedido.finalizarPedido(filaPreparo);
        // usa getTamanho() de PedidosAtivos
        System.out.println("Pedidos ativos restantes: " + pedidosAtivos.getTamanho());
    }
}