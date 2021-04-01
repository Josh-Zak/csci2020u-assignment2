package Client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Frame {
//    public static void main(String args[]) {
//        BufferedReader in;
//        PrintWriter out;
//
//        String hostName = args[0];
//        int port = 16789;
//        if(args.length > 1) {
//            port = Integer.parseInt(args[1]);
//        }
//        try {
//            Socket socket = new Socket(hostName,port);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(socket.getOutputStream());
//            String message = in.readLine();
//            while (message != null) {
//                System.out.println(message);
//            }
//            in.close();
//            out.close();
//            socket.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


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
