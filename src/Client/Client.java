package Client;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import static Client.Main.controllerHandler;

public class Client extends Frame {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    public static String SERVER_ADDRESS = Main.getArgs().get(1);
    public static int SERVER_PORT = Integer.parseInt(Main.getArgs().get(0));
//    public static List<String> argument = controllerHandler.getArgs();
//    public static String SERVER_ADDRESS = argument.get(1);
//    public static int SERVER_PORT = Integer.parseInt(argument.get(0));

    public Client(){
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(socket == null){
            System.err.println("Socket is null");
        }else{
            try{
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                String message = in.readLine();//connected to server (msg from server)
                System.out.println(message);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void send(String msg){
        out.println(msg);
    }
}
