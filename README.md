# 🍔 Sistema de Controle de Pedidos e Entregas (Delivery)

Este projeto é um sistema de gerenciamento de pedidos para uma empresa de delivery desenvolvido em Java. O sistema foca no uso e implementação de **Estruturas de Dados Dinâmicas** construídas do zero (sem o uso de bibliotecas prontas como `ArrayList` ou `LinkedList`), aplicando conceitos rigorosos de Engenharia de Software e Programação Orientada a Objetos.

## 📌 Funcionalidades
* Cadastro e listagem de Clientes e Produtos.
* Criação de Pedidos vinculados a clientes.
* Adição de itens (`ItemPedido`) a pedidos abertos.
* Navegação iterativa entre pedidos ativos.
* Controle de fluxo de preparação (Cozinha) e Histórico de Entregas.

## 🏗️ Decisões de Arquitetura e Heurísticas

### 1. Uso de Generics (`<P>`) na Lista Simplesmente Encadeada
Para evitar a duplicação de código — que ocorreria ao criar uma classe `ListaCliente` e outra `ListaProduto` separadas —, foi adotada a heurística de **Generics**. A classe `ListaPedidos<P>` atua como uma estrutura agnóstica de tipo. 
Isso significa que, durante a instanciação, o sistema define qual tipo de dado a lista irá armazenar:
* `ListaPedidos<Cliente>`: Gerencia o banco de clientes.
* `ListaPedidos<Produto>`: Gerencia o catálogo do menu.
* `ListaPedidos<ItemPedido>`: Fica embutida dentro da classe `Pedido` para gerenciar o carrinho.
Isso promove alta reutilização de código e segurança de tipagem em tempo de compilação.

### 2. Encapsulamento de Nós (Inner Classes)
As classes base (`Node`) responsáveis por armazenar o dado (`data`) e os ponteiros (`next` e `prev`) não foram criadas em arquivos externos e públicos. Ao invés disso, elas foram definidas como **Classes Internas Estáticas e Privadas** (`private static class Node`) dentro de cada estrutura de dados correspondente.

**Por que isso foi feito?**
Isso garante um **encapsulamento estrito**. A classe `DeliverySystem` (Controlador) não tem permissão para enxergar ou manipular diretamente a "cabeça" (`head`), a "cauda" (`tail`) ou os ponteiros dos nós. O sistema interage com as estruturas apenas por meio de métodos de alto nível (`adicionar`, `empilhar`, `desenfileirar`), protegendo as listas contra corrupção acidental de memória.

## 🗂️ Estruturas de Dados Implementadas

O fluxo do sistema utiliza diferentes estruturas dinâmicas para simular o processo real de um restaurante:

* **Lista Simplesmente Encadeada:** Usada para catálogos estáticos onde a busca é sequencial (Clientes, Produtos e Itens do Pedido). Possui ponteiro de via única (`next`).
* **Lista Duplamente Encadeada:** Controla os **Pedidos Ativos** em edição. Graças aos ponteiros duplos (`next` e `prev`), permite ao atendente avançar ou voltar pelos pedidos abertos sem precisar reiniciar a busca.
* **Fila Encadeada (Queue - FIFO):** Controla a **Cozinha**. Os pedidos entram no final da fila (`tail`) e são preparados a partir do início (`head`), respeitando a ordem de chegada.
* **Pilha Encadeada (Stack - LIFO):** Controla o **Histórico de Entregas**. O último pedido finalizado pela cozinha entra no topo da pilha, garantindo que as consultas ao histórico exibam sempre os dados mais recentes primeiro.

## 🚀 Como Executar

1. Certifique-se de ter o [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) instalado.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/murilomunizzdias/ProjetoIsaac.git](https://github.com/murilomunizzdias/ProjetoIsaac.git)
