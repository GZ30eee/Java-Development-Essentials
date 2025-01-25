import java.io.*;
import java.net.*;

public class ChatClient {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 12345);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            Thread receiveThread = new Thread(new ReceiveMessages());
            receiveThread.start();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message); // Send message to server
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    static class ReceiveMessages implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message); // Display messages received from server
                }
            } catch (IOException e) {
                System.out.println("Error receiving messages: " + e.getMessage());
            }
        }
    }
}
