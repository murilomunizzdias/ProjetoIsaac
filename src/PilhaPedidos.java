public class PilhaPedidos {

    private static class Node {
        Pedido pedido;
        Node next;

        Node(Pedido pedido) {
            this.pedido = pedido;
            this.next = null;
        }
    }

    private Node head;
    private int tamanho;

    public PilhaPedidos() {
        this.head = null;
        this.tamanho = 0;
    }

    public void empilhar(Pedido novoPedido) {
        Node novoNode = new Node(novoPedido);

        novoNode.next = head;

        head = novoNode;
        tamanho++;

        System.out.println("Pedido #" + novoPedido.getIdPedido() + " registrado no histórico de concluídos.");
    }

    public Pedido desempilhar() {
        if (estaVazia()) {
            System.out.println("O histórico está vazio.");
            return null;
        }

        Pedido pedidoRemovido = head.pedido;

        head = head.next;
        tamanho--;

        return pedidoRemovido;
    }

    public void exibirHistorico() {
        if (estaVazia()) {
            System.out.println("Nenhum pedido finalizado no histórico ainda.");
            return;
        }

        System.out.println("-=-=-=-=-=- HISTÓRICO DE PEDIDOS (Mais recentes primeiro) -=-=-=-=-=-");
        Node aux = head;
        while (aux != null) {
            System.out.println(aux.pedido.toString());
            aux = aux.next;
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public boolean estaVazia() {
        return head == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}