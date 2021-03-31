package sample;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Frame {
//figure out how to connect this to the controller and the UI

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 16789;

    public Client(){
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(socket == null){
            System.err.println("Socket is null");
        }
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            //print connected to server message from serverThread
            String message = in.readLine();
            System.out.println(message);
        }catch(Exception e){
            e.printStackTrace();
        };
    }
}
