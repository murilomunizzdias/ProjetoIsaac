public class ItemPedido {
    Produto produto;
    int quantidade;
    double subtotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.preco * quantidade;
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return quantidade + "x " + produto.nome + " (Subtotal: R$ " + subtotal + ")";
    }
}
