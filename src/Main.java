public class Main {
    public static void main(String[] args) {

        // 1. Cria o cliente vazio e manda ele se cadastrar
        Cliente cliente1 = new Cliente();
        cliente1.cadastrar();

        // 2. Exibe como os dados ficaram salvos
        cliente1.exibirDados();

        // 3. Chama o método de atualizar

        cliente1.atualizarDados();

        // 4. Exibe novamente para confirmar a mudança
        cliente1.exibirDados();
    }
}