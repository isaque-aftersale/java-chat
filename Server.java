import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.util.Scanner;

public class Server {
    private static ServerSocket server;
    private static Scanner inputScanner;
    private static final Integer PORT = 3000;

    public static void main(String[] args) {

        try {
            server = new ServerSocket(PORT);

            server.setReuseAddress(true);

            do {
                handleClient(server);
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleClient(ServerSocket server) {
        try {
            Socket client = server.accept();

            inputScanner = new Scanner(client.getInputStream());

            while (inputScanner.hasNextLine()) {
                System.out.println("client-" + client.getInetAddress().getHostName() + ": " + inputScanner.nextLine());

                if (!client.isBound()) {
                    client.bind(new InetSocketAddress(server.getInetAddress(), 3000));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
