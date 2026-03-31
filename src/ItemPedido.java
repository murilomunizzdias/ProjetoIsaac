public class ItemPedido {
    Produto produto;
    int quantidade;
    double subtotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void calcularSubtotal() {
        if (this.produto != null) {
            this.subtotal = this.produto.preco * this.quantidade;
        }
    }
}