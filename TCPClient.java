import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1"; // Localhost
        int serverPort = 5000;

        try {
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to server.");

            // Input and Output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send message to server
            String message = "Hello Server!";
            out.println(message);
            System.out.println("Sent to server: " + message);

            // Receive response from server
            String response = in.readLine();
            System.out.println("Received from server: " + response);

            // Close connections
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
