package cortez.vinicius.client;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas  {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Conexão Estabelecida");

        Thread enviaComando = new Thread(() -> {
            try {
                System.out.println("Pode enviar comandos");
                Scanner teclado = new Scanner(System.in);
                PrintStream saida = new PrintStream(socket.getOutputStream());

                while (teclado.hasNextLine()) {
                    String linha = teclado.nextLine();
                    if (linha.trim().equals("")) break;
                    saida.println(linha);
                    saida.close();
                    teclado.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        enviaComando.start();


        // Resposta
        Thread recebeResposta = new Thread(() -> {
            try {
                System.out.println("Recebendo dados do servidor!");
                Scanner respotaServidor = new Scanner(socket.getInputStream());
                while (respotaServidor.hasNextLine()) {
                    String linha = respotaServidor.nextLine();
                    System.out.println(linha);
                }

                respotaServidor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        recebeResposta.start();
        enviaComando.join();

        System.out.println("Fechando o socket do cliente");
        socket.close();


    }

}
