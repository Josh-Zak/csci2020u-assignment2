package Server;

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
        StringTokenizer tokenizer = new StringTokenizer(message);
        String command = tokenizer.nextToken();
        String args = null;
        if(tokenizer.hasMoreTokens()){
            args = message.substring(command.length() + 1, message.length());
        }
        if(null != args) {
            return process(command, args);
        }else{
            return process(command);
        }

    }

    protected boolean process(String command, String args){
        if(command.equalsIgnoreCase("DIR")){
            System.out.println("dir");
            return true;
        }else if(command.equalsIgnoreCase("UPLOAD")){
            System.out.println("upload");
//in.print(file contents) to the new file on server side
            return true;
        }else if(command.equalsIgnoreCase("DOWNLOAD")){
            System.out.println("download");
//out.print(file contents) to the new file on the client side
            return true;
        }else{
            out.println("Not a command");
            return true;
        }
    }










    //this method is for testing
    protected boolean process(String command){
        if(command.equalsIgnoreCase("DIR")){
            System.out.println("dir");
            return true;
        }else if(command.equalsIgnoreCase("UPLOAD")){
            System.out.println("upload");
//in.print(file contents) to the new file on server side
            return true;
        }else if(command.equalsIgnoreCase("DOWNLOAD")){
            System.out.println("download");
//out.print(file contents) to the new file on the client side
            return true;
        }else{
            out.println("Not a command");
            return true;
        }
    }
}
