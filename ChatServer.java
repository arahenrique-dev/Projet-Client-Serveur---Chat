import java.io.IOException;
import java.net.*;

public class ChatServer {
    public static void main(String args[]) throws IOException {
        int port = 1234;
        ServerSocket serv = new ServerSocket(port);
        System.out.println("Server initialisé au port " + port);
        ChatRoom room = new ChatRoom();

        while(true) {
            Socket client = serv.accept();
            ClientThread clientThread = new ClientThread(client, room);
            clientThread.start();
        }
    }
}
