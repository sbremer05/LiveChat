import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 14253;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADRESS, SERVER_PORT);
            System.out.println("Connected to char server!");

//            Set up input and output streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                try {
                    String serverResponse;
                    while((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }).start();

//            Read messages from console and send to server
            Scanner scanner = new Scanner(System.in);
            String userInput;
            while(true) {
                userInput = scanner.nextLine();
                out.println(userInput);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}