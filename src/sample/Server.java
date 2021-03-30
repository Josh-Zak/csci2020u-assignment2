package sample;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread[] threads = null;
    protected int numClients = 0;

    public static int SERVER_PORT = 16789;
    public static int MAX_CLIENTS = 25;

    public Server(){
        try{
            serverSocket = new ServerSocket(numClients);
            System.out.println("Application is Running");
            System.out.println("Listening to port " + SERVER_PORT);
            threads = new ServerThread[MAX_CLIENTS];
            while(true){
                clientSocket = serverSocket.accept();
                threads[numClients] = new ServerThread(clientSocket);
                threads[numClients].start();
                numClients++;
//might need to add a way to destroy clients and do numClients--
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
