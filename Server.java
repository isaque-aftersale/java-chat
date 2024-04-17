import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ServerSocket server;
    private static Socket client;
    private static Scanner inputScanner;
    private static final Integer PORT = 3000;

    public static void main(String[] args) {

        try {
            server = new ServerSocket(PORT);

         

            do {
                client = server.accept();
                inputScanner = new Scanner(client.getInputStream());

                handleClient();
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleClient() {
        while (!inputScanner.hasNextLine())
            return;

        System.out.println("client-" + client.getInetAddress().getHostName() + ": " + inputScanner.nextLine());

    }
}
