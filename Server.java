import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Scanner;

public class Server {
    private static final int PORT = 5000;
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started, waiting for clients...");

//            Thread to handle server admin input
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String serverMessage = scanner.nextLine();
                    broadcast("[Server]: " + serverMessage, null);
                }
            }).start();

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

//                Create new client handler for client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

//    Broadcast message to clients
    public static void broadcast(String message, ClientHandler sender) {
        for(ClientHandler client : clients) {
            if(client != sender) {
                cliend.sendMessage(message);
            }
        }
    }

//    Internal class for handling client connections
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                out = new PrintWriter(clientSocket.getOutputSteam(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputSteam()));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
//                Get username from client
                out.println("Enter your username:");
                username = in.readLine();
                System.out.println("Username: " + username + " saved.");
                out.println("Welcome to the server " + username + "!");
                out.println("Type your message:");

                String inputLine;
                while((inputLine = in.readLine) != null) {
                    System.out.println("[" + username + "]: " + inputLine);
                    broadcast("[" + username + "]: " + inputLine, this);
                }

//                Remove client hander from list
                clients.remove(this);
                System.out.println("Client " + username + " disconnected.");
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}