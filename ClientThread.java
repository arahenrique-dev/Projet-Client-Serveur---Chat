import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket client;
    private ChatRoom chatRoom;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private String password;

    public ClientThread(Socket socket, ChatRoom room) throws IOException {
        this.client = socket;
        this.chatRoom = room;
    }
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            
            //User Authentication
            out.println("Votre username: ");
            username = in.readLine();
            out.println("Votre mot de passe: ");
            password = in.readLine();

            if (authenticateUser(username, password)) {
                
                chatRoom.add(this);
                chatRoom.broadcast_msg("\033[1m" + username + "\033[0m est entré dans le chat !");
                this.send("Bienvenu(e) " + username + " !");
                
                String message;
                while ((message = in.readLine()) != null) {
                    chatRoom.broadcast_msg("\033[1m" + username + "\033[0m : " + message);
                }
            }
            else {
                this.send("Le login a echoué.");
                client.close();
                return;
            }
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean authenticateUser(String username, String password) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line = br.readLine();
            while (line != null) {
                String[] userData = line.split(":");
                line = br.readLine();
                if (userData[0].equals(username) && userData[1].equals(password))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public void send(String msg) {
        out.println(msg);
    }
}