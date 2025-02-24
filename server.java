import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345; // Port number

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            // Accept a connection from a client
            Socket socket = serverSocket.accept();
            System.out.println("Connection from " + socket.getInetAddress());

            // Get input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                // Print the received message from the client
                System.out.println("Received from client: " + message);

                // Send response back to the client
                String response = "Server received your message: " + message;
                output.println(response);

                // Break the loop if the client sends 'bye'
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            // Close the connections
            socket.close();
            input.close();
            output.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
