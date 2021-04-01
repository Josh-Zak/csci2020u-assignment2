package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread threads = null;
    public static int SERVER_PORT;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.SERVER_PORT = port;
    }

    public void handleRequests() {
        try{
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Application is Running");
            System.out.println("Listening to port " + SERVER_PORT);
            while(true){
                clientSocket = serverSocket.accept();
                threads = new ServerThread(clientSocket);
                //Thread handlerThread = new Thread(threads);
                //handlerThread.start();
                threads.start();
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
