package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

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
        String message = null;
        try{
            message = in.readLine();
        }catch(Exception e){
            e.printStackTrace();
            return true;
        }
        if(message == null){
            return true;
        }
        StringTokenizer tokenizer = new StringTokenizer(message);
        String command = tokenizer.nextToken();
        String args = null;
        if(tokenizer.hasMoreTokens()){
            args = message.substring(command.length() + 1, message.length());
        }
        return process(command, args);
    }

    protected boolean process(String command, String args){
        if(command.equalsIgnoreCase("DIR")){

            return true;
        }else if(command.equalsIgnoreCase("UPLOAD")){
            return true;
        }else if(command.equalsIgnoreCase("DOWNLOAD")){
            return true;
        }else{
            out.println("Not a command");
            return true;
        }
    }
}
