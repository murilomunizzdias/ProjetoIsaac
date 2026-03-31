public class FilaPedidos {
    private static class Node {
        Pedido pedido;
        Node proximo;

        Node(Pedido pedido) {
            this.pedido = pedido;
            this.proximo = null;
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
            fim.proximo = novoNode;
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
        frente = frente.proximo;

        if (frente == null) {
            fim = null;
        }
        
        tamanho--;
        System.out.println("Pedido #" + pedidoSaindo.getIdPedido() + " pronto para entrega!");
        return pedidoSaindo;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void verProximoDaFila() {
        if (estaVazia()) {
            System.out.println("Nenhum pedido em espera.");
        } else {
            System.out.println("Próximo a ser preparado: Pedido #" + frente.pedido.getIdPedido());
        }
    }
}
