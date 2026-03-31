public class Pedido {
    int idPedido;
    String cliente;
    String status;
    double valorTotal;
    String enderecoEntrega;
    ListaPedidos<ItemPedido> listaItens;

    public Pedido(int id, String cliente, String endereco) {
        this.idPedido = id;
        this.cliente = cliente;
        this.enderecoEntrega = endereco;
        this.status = "Aberto";
        this.listaItens = new ListaPedidos<>();
        this.valorTotal = 0.0;
    }

     public ListaPedidos<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void finalizarPedido(FilaPedidos fila) {
        this.status = "Em Preparação";
        fila.enfileirar(this);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void adicionarItem(ItemPedido item) {
        listaItens.adicionar(item);
        this.valorTotal += item.getSubtotal();
        System.out.println("Item adicionado com sucesso!");
    }

    public void removerItem(ItemPedido item) {
        if (item != null) {
            listaItens.remover(item);
            this.valorTotal -= item.getSubtotal();
        }
    }

    public void exibirResumo() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("RESUMO DO PEDIDO #" + idPedido);
        System.out.println("Cliente: " + cliente);
        System.out.println("Status: " + status);
        System.out.println("Itens:");
        listaItens.exibirTodos();
        System.out.printf("TOTAL: R$ %.2f%n", valorTotal);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - Cliente: " + cliente + " (R$ " + valorTotal + ")";
    }
}
