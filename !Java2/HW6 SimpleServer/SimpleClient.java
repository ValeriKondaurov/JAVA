/**
 * Write a description of class SimpleChatBot here.
 *
 * @author Valeriy Kondaurov
 * @version dated Jan 15, 2017
 * @link https://github.com/ValeriKondaurov/Java2HW6
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;

class SimpleClient {

    final String SERVER_ADDR = "127.0.0.1"; // or "localhost"
    final int SERVER_PORT = 2048;
    final String CLIENT_PROMPT = "$ ";
    final String CONNECT_TO_SERVER = "Connection to server established.";
    final String CONNECT_CLOSED = "Connection closed.";
    final String EXIT_COMMAND = "exit"; // command for exit

    public static void main(String[] args) {
        new SimpleClient();
    }

    SimpleClient() {
        String message;
        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            ) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println(CONNECT_TO_SERVER);
            do {
                message = reader.readLine();
                System.out.println(message);
                System.out.print(CLIENT_PROMPT);
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
            } while (!message.equalsIgnoreCase(EXIT_COMMAND));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECT_CLOSED);
    }
}