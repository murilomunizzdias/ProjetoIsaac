public class ListaPedidos<P> {

    private static class Node<P> {
        P data;
        Node<P> next;

        Node(P data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<P> inicio;

    public void adicionar(P data) {
        Node<P> novo = new Node<>(data);
        if (inicio == null) {
            inicio = novo;
        } else {
            Node<P> aux = inicio;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = novo;
        }
    }

    public void exibirTodos() {
        Node<P> aux = inicio;
        if (aux == null) {
            System.out.println("Lista vazia.");
            return;
        }
        while (aux != null) {
            System.out.println(" - " + aux.data.toString());
            aux = aux.next;
        }
    }

    public boolean remover(P data) {
        if (inicio == null || data == null) return false;

        if (inicio.data.equals(data)) {
            inicio = inicio.next;
            return true;
        }

        Node<P> aux = inicio;
        while (aux.next != null && !aux.next.data.equals(data)) {
            aux = aux.next;
        }

        if (aux.next != null) {
            aux.next = aux.next.next;
            return true;
        }

        return false;
    }

    public P buscarPorNome(String nome) {
        if (nome == null) return null;
        Node<P> aux = inicio;
        while (aux != null) {
            // Compara o toString() inteiro em lowercase para evitar falsos positivos
            // A comparação é feita apenas no início do toString() para ser mais precisa
            String dados = aux.data.toString().toLowerCase();
            String busca = nome.toLowerCase().trim();
            // Verifica se algum "campo" do toString contém exatamente a busca
            if (dados.contains("nome: " + busca) || dados.startsWith(busca) || dados.equals(busca)) {
                return aux.data;
            }
            aux = aux.next;
        }
        return null;
    }

    // Retorna o primeiro elemento que passa no teste (usado para busca por ID, etc.)
    public P buscarPorCondicao(java.util.function.Predicate<P> condicao) {
        Node<P> aux = inicio;
        while (aux != null) {
            if (condicao.test(aux.data)) return aux.data;
            aux = aux.next;
        }
        return null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}