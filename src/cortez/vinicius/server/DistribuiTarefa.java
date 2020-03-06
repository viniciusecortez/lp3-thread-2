package cortez.vinicius.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuiTarefa implements Runnable {

    private Socket socket;

    public DistribuiTarefa(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Distribuindo tarefas para o cliente: " + socket);
        try {
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();
                System.out.println("Comando: " + comando);

                switch (comando) {
                    case "comando":
                        saidaCliente.println("Outro comando");
                        break;

                    default:
                        saidaCliente.println("Help!!!!");
                }
            }
            entradaCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

