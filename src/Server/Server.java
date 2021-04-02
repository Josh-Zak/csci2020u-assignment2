package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Server {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread handler = null;
    private int SERVER_PORT;
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.SERVER_PORT = port;
    }

    public void handleRequests() {
        try{
            System.out.println("Application is Running");
            System.out.println("Listening to port " + SERVER_PORT);
            while(true){
                clientSocket = serverSocket.accept();
                handler = new ServerThread(clientSocket);
                Thread handlerThread = new Thread(handler);
                handlerThread.start();
                // no need for destroying the client
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        int port = 16789;
        try {
            Server fileShareServer = new Server(port);
            fileShareServer.handleRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
