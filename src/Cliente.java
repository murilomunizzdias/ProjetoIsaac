import java.util.Scanner;

public class Cliente {
    int idCliente;
    String nome;
    String telefone;
    String endereco;
    static int contador = 1;

    public Cliente() {
        this.idCliente = contador++;
    }

    public void cadastrar(Scanner input) {
        System.out.println("Insira o nome do cliente: ");
        this.nome = input.nextLine();
        System.out.println("Insira o telefone do cliente: ");
        this.telefone = input.nextLine();
        System.out.println("Insira o endereco do cliente: ");
        this.endereco = input.nextLine();
        
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.println("Cliente: " + this.nome + " cadastrado com sucesso! ");
        System.out.println("Id: " + this.idCliente);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }

    public void atualizarDados(Scanner input) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Atualizando dados do Cliente:\n");

        System.out.println("Nome atual: " + this.nome);
        System.out.print("Insira o novo nome: ");
        this.nome = input.nextLine();

        System.out.println("Telefone atual: " + this.telefone);
        System.out.print("Insira o novo telefone: ");
        this.telefone = input.nextLine();

        System.out.println("Endereço atual: " + this.endereco);
        System.out.print("Insira o novo endereço: ");
        this.endereco = input.nextLine();

        System.out.println("Dados atualizados com sucesso!");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }

    public void exibirDados() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Ficha do Cliente: \n");
        System.out.println("ID: " + this.idCliente);
        System.out.println("Nome: " + this.nome);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    @Override
    public String toString() {
        return "ID: " + idCliente + " | " + nome + " | " + telefone;
    }
}
