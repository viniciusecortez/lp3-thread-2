package cortez.vinicius.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("-------Iniciando Servidor-------");
        ServerSocket servidor = new ServerSocket(12345);
        // Aceitando requisição
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        ExecutorService pool = Executors.newCachedThreadPool();

        while(true){
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta: "+socket);
            pool.execute(new DistribuiTarefa(socket));
        }

    }
}
