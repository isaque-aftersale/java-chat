import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static InetAddress HOST = null;
    private static Integer PORT = 3000;
    private static PrintStream printOutput;
    private static String text = "";
    private static Scanner keyboard = null;

    public static void main(String[] args) {
        Socket client = null;

        try {
            HOST = InetAddress.getByName("localhost");

            client = new Socket(HOST, PORT);
            printOutput = new PrintStream(client.getOutputStream());
            keyboard = new Scanner(System.in);

            do {

                handleClient(client);

            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleClient(Socket client) throws Exception {
        while (!keyboard.hasNextLine())
            return;

        text = keyboard.nextLine();

        printOutput.println(text);

        if (text.equals("end")) {
            client.close();
            keyboard.close();
            System.out.println("--- CLIENT CLOSED ----");
            System.exit(1);
        }
    }
}