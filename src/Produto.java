import java.util.Scanner;

public class Produto {
    int idProduto;
    String nome;
    double preco;
    String categoria;
    int estoque;
    static int contador = 1;

    public Produto(String nome, double preco, String categoria, int estoque) {
        this.idProduto = contador++;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
    }

    public void atualizarEstoque(int quantidade) {
        this.estoque += quantidade;
        System.out.println("Estoque do produto '" + this.nome + "' atualizado. Nova quantidade: " + this.estoque);
    }

    public void exibirProduto() {
        System.out.println("Ficha do Produto: ");
        System.out.println("ID: " + this.idProduto);
        System.out.println("Nome: " + this.nome);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Preço: R$ " + this.preco);
        System.out.println("Estoque: " + this.estoque + " unidades");
    }
}