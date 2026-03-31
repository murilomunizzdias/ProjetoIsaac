class ListaPedidos<P> {
    
    private static class Node<P> {
        P dado;
        Node<P> proximo;

        Node(P dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private Node<P> inicio;

    public void adicionar(P dado) {
        Node<P> novo = new Node<>(dado);
        if (inicio == null) {
            inicio = novo;
        } else {
            Node<P> aux = inicio;
            while (aux.proximo != null) {
                aux = aux.proximo;
            }
            aux.proximo = novo;
        }
    }

    public void exibirTodos() {
        Node<P> aux = inicio;
        if (aux == null) {
            System.out.println("Lista vazia.");
            return;
        }
        while (aux != null) {
            System.out.println(" - " + aux.dado.toString());
            aux = aux.proximo;
        }
    }

    public void remover(P dado) {
        if (inicio == null || dado == null) return;

        if (inicio.dado.equals(dado)) {
            inicio = inicio.proximo;
            return;
        }

        Node<P> aux = inicio;
        while (aux.proximo != null && !aux.proximo.dado.equals(dado)) {
            aux = aux.proximo;
        }

        if (aux.proximo != null) {
            aux.proximo = aux.proximo.proximo;
        }
    }

    public P buscarPorNome(String nome) {
        if (nome == null) return null;
        Node<P> aux = inicio;
        while (aux != null) {
            if (aux.dado.toString().toLowerCase().contains(nome.toLowerCase())) {
                return aux.dado;
            }
            aux = aux.proximo;
        }
        return null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
