import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Server's IP address (localhost)
        int port = 12345; // Server's port number

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(host, port);
            System.out.println("Connected to the server.");

            // Get input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            System.out.println("Enter message to send to server (type 'bye' to exit):");
            while (true) {
                // Read message from user input
                message = input.readLine();
                
                // Send message to the server
                output.println(message);
                
                // Exit the loop if the user types 'bye'
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }

                // Receive and print the server's response
                String serverResponse = serverInput.readLine();
                System.out.println("Received from server: " + serverResponse);
            }

            // Close the connections
            socket.close();
            input.close();
            serverInput.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
