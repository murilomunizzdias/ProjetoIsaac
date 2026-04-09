import java.util.Scanner;
public class PedidosAtivos {

    private static class Node{
        Node next;
        Node prev;
        Pedido pedido;

        Node(Pedido pedido){
            this.pedido = pedido;
            this.next = null;
            this.prev = null;
        }

    }

    private Node head;
    private Node tail;
    private int tamanho;

    public PedidosAtivos() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

    public void adicionar(Pedido novoPedido) {
        Node novoNode = new Node(novoPedido);

        if (estaVazia()) {
            head = novoNode;
            tail = novoNode;
        } else {
            tail.next = novoNode;
            novoNode.prev = tail;
            tail = novoNode;
        }
        tamanho++;
    }

    public void removerPorId(int idPedido) {
        if (estaVazia()) return;

        Node atual = head;

        while (atual != null && atual.pedido.getIdPedido() != idPedido) {
            atual = atual.next;
        }

        if (atual == null) {
            System.out.println("Pedido #" + idPedido + " não encontrado nos ativos.");
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else if (atual == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (atual == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            atual.prev.next = atual.next;
            atual.next.prev = atual.prev;
        }
        tamanho--;
    }

    // Exibe todos os pedidos ativos
    public void exibirTodos() {
        if (estaVazia()) {
            System.out.println("Nenhum pedido ativo no momento.");
            return;
        }
        Node atual = head;
        System.out.println("-=-=-=-=-=- PEDIDOS ATIVOS -=-=-=-=-=-");
        while (atual != null) {
            System.out.println(atual.pedido.toString());
            atual = atual.next;
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    // Navegação interativa entre os pedidos
    public void navegar(Scanner scanner) {
        if (estaVazia()) {
            System.out.println("Não há pedidos ativos para navegar.");
            return;
        }

        Node atual = head;
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- NAVEGANDO NOS PEDIDOS ---");
            atual.pedido.exibirResumo();
            System.out.println("\n[1] Próximo Pedido  |  [2] Pedido prev  |  [3] Sair da Navegação");
            System.out.print("Escolha: ");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
            } else {
                scanner.nextLine();
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    if (atual.next != null) {
                        atual = atual.next;
                    } else {
                        System.out.println(">> Você já está no ÚLTIMO pedido da lista! <<");
                    }
                    break;
                case 2:
                    if (atual.prev != null) {
                        atual = atual.prev;
                    } else {
                        System.out.println(">> Você já está no PRIMEIRO pedido da lista! <<");
                    }
                    break;
                case 3:
                    System.out.println("Saindo da navegação...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public boolean estaVazia() {
        return head == null;
    }

    public int getTamanho() {
        return tamanho;
    }


    public Pedido buscarPorId(int idPedido) {
        Node atual = head;
        while (atual != null) {
            if (atual.pedido.getIdPedido() == idPedido) {
                return atual.pedido;
            }
            atual = atual.next;
        }
        return null;
    }
}