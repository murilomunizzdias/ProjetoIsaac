class ListaPedidos<P> {
    
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

    public void remover(P data) {
        if (inicio == null || data == null) return;

        if (inicio.data.equals(data)) {
            inicio = inicio.next;
            return;
        }

        Node<P> aux = inicio;
        while (aux.next != null && !aux.next.data.equals(data)) {
            aux = aux.next;
        }

        if (aux.next != null) {
            aux.next = aux.next.next;
        }
    }

    public P buscarPorNome(String nome) {
        if (nome == null) return null;
        Node<P> aux = inicio;
        while (aux != null) {
            if (aux.data.toString().toLowerCase().contains(nome.toLowerCase())) {
                return aux.data;
            }
            aux = aux.next;
        }
        return null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
