import java.util.ArrayList;

public class ChatRoom {
    private ArrayList<ClientThread> connections;

    public ChatRoom() {
        this.connections = new ArrayList<>();
    }

    public synchronized void add(ClientThread c) {
        connections.add(c);
    }
    public synchronized void remove(ClientThread c) {
        connections.remove(c);
    }
    
    public void broadcast_msg(String msg) {
        for(ClientThread c : connections) {
            c.send(msg);
        }
    }
}