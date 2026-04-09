public class FilaPedidos {
    private static class Node {
        Pedido pedido;
        Node next;

        Node(Pedido pedido) {
            this.pedido = pedido;
            this.next = null;
        }
    }

    private Node frente;
    private Node fim;
    private int tamanho;

    public FilaPedidos() {
        this.frente = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void enfileirar(Pedido novoPedido) {
        Node novoNode = new Node(novoPedido);

        if (estaVazia()) {
            frente = novoNode;
            fim = novoNode;
        } else {
            fim.next = novoNode;
            fim = novoNode;
        }
        tamanho++;
        System.out.println("Pedido #" + novoPedido.getIdPedido() + " enviado para a cozinha!");
    }

    public Pedido desenfileirar() {
        if (estaVazia()) {
            System.out.println("A fila de preparação está vazia.");
            return null;
        }

        Pedido pedidoSaindo = frente.pedido;
        frente = frente.next;

        if (frente == null) {
            fim = null;
        }

        tamanho--;
        System.out.println("Pedido #" + pedidoSaindo.getIdPedido() + " pronto para entrega!");
        return pedidoSaindo;
    }

    public void exibirFila() {
        if (estaVazia()) {
            System.out.println("Nenhum pedido em preparo no momento.");
            return;
        }
        System.out.println("-=-=-=-=-=- PEDIDOS EM PREPARO (ordem de chegada) -=-=-=-=-=-");
        Node aux = frente;
        int posicao = 1;
        while (aux != null) {
            System.out.println(posicao + "º - " + aux.pedido.toString() + " | Status: " + aux.pedido.status);
            aux = aux.next;
            posicao++;
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void vernextDaFila() {
        if (estaVazia()) {
            System.out.println("Nenhum pedido em espera.");
        } else {
            System.out.println("Próximo a ser preparado: Pedido #" + frente.pedido.getIdPedido());
        }
    }
}