package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    protected Socket socket = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;


    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        out.println("Connected to Server");
        boolean endOfSession = false;
        while(!endOfSession){
            endOfSession = process();
        }
        try{
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    protected boolean process(){

        return true; //TEMP
    }
}
