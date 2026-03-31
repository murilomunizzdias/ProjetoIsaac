import java.util.Scanner;

public class Cliente {
    int idCliente;
    String nome;
    String telefone;
    String endereco;
    static int contador=0;

    public Cliente() {
        idCliente = contador++;
    }

    //adisson e lucas bora usar assim o metodo cadasstrar pq ele pediu, esse id que eu fiz
    //é aquele que vai atualizar sozinho :)

    public void cadastrar(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insira o nome do cliente: ");
        nome = input.nextLine();
        System.out.println("Insira o telefone do cliente: ");
        telefone = input.nextLine();
        System.out.println("Insira o endereco do cliente: ");
        endereco = input.nextLine();

        System.out.println("Cliente: "+this.nome+"cadastrado com sucesso!"+"Id:"+this.idCliente);

    }

    public void atualizarDados() {
        Scanner input = new Scanner(System.in);

        System.out.println("Atualizando dados do Cliente:\n");

        System.out.println("Nome atual: " + this.nome);
        System.out.println("Insira o novo nome: ");
        this.nome = input.nextLine();

        System.out.println("Telefone atual: " + this.telefone);
        System.out.println("Insira o novo telefone: ");
        this.telefone = input.nextLine();

        System.out.println("Endereço atual: " + this.endereco);
        System.out.println("Insira o novo endereço: ");
        this.endereco = input.nextLine();

        System.out.println("Dados atualizados com sucesso!");
    }

    public void exibirDados() {
        System.out.println("Ficha do Cliente: \n");
        System.out.println("ID: " + this.idCliente);
        System.out.println("Nome: " + this.nome);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Endereço: " + this.endereco);
    }

}
