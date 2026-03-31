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

    public String getNome() { 
        return nome; 
    }
    
    public double getPreco() { 
        return preco; 
    }

    public void exibirProduto() {
        System.out.println("ID: " + idProduto + " | " + nome + " | R$ " + preco);
    }

    @Override
    public String toString() { return nome; }
}
