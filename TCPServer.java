import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000; // Server will listen on this port
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client...");

            Socket clientSocket = serverSocket.accept(); // Accept client connection
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Input and Output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Receive message from client
            String message = in.readLine();
            System.out.println("Received from client: " + message);

            // Send response to client
            out.println("Hello Client, I received: " + message);

            // Close connections
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
