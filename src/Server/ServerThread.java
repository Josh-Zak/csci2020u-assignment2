package Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
    protected Socket socket = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;

    // create a field that stores the list of the contents of the shared folder

    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
        try{
            out = new PrintWriter(socket.getOutputStream(), true); //write stuff to the client
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //read stuff from the client
        }catch(Exception e){
            e.printStackTrace();
        }
    }
// we can merge ServerHandler and ServerThread into 1
    public void run(){
        out.println("Connected to Server"); // print the message to the client
        String line = null;
        try {
            line = in.readLine();
            process(line);
        }catch(IOException e) {
            e.printStackTrace();
        }
        try{
            in.close();
            out.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
// handle the request from the client in this function
    protected boolean process(String message){
        StringTokenizer tokenizer = new StringTokenizer(message);
        String command = tokenizer.nextToken(); //first token of the message, usually the command
        String args = null;
        if(tokenizer.hasMoreTokens()){
            // if the message has more than 2 arguments, pass from the second word in the message as args
            args = message.substring(command.length() + 1, message.length());
        }
        if(null != args) {
            return process(command, args);
        }else{
            System.err.println("No arguments given");
            return true;
        }

    }

    protected boolean process(String command, String args){
        if(command.equalsIgnoreCase("DIR")){
            out.println("dir");
            String[] fileName = handleDir(args);
            for(String files:fileName){
                out.println(files);
            }
            return true;
        }else if(command.equalsIgnoreCase("UPLOAD")){
            //handleUpload();
            out.println("upload");
//in.print(file contents) to the new file on server side
            return true;
        }else if(command.equalsIgnoreCase("DOWNLOAD")){
            //handleDownload();
            out.println("download");
//out.print(file contents) to the new file on the client side
            return true;
        }else{
            out.println("Not a command");
            return true;
        }
    }

// create 3 functions to handle the commands from the client

    // Returns a listing of the contents of the shared folder
    public String[] handleDir(String folderPath){
        File file = new File(folderPath);
        if(!file.exists()){
            System.err.println("The folder doesn't exist. Please try again.");
        }
        String[] fileList = file.list();
        return fileList;
    }
    // functionality of upload: we have the parameter as the path to the shared folder of the local client
    // create a new shared folder for the server if not created
    // read the text from the selected file from the client folder (fileName)
    // create a new file in the server folder (copyFile) and write all of the texts from fileName to copyFile
    public void handleUpload(File baseDir,String fileName) {
        File clientFile = new File(baseDir, fileName);
        File serverFile = new File("./shared");
        String line = "";

        if (!clientFile.exists()) {
            System.err.println("The file " + clientFile + " could not be located.");
        } else {
            try {
                FileWriter fw = new FileWriter(serverFile);
                BufferedReader br = new BufferedReader(new FileReader(clientFile));
                while((line = br.readLine())!= null) {
                    fw.write(line);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    //functionality of download: read the text in the fileName file,
    // print out the text on the client console
    public void handleDownload(String fileName){
        File serverFile = new File("shared",fileName);
        String line = "";
        if(!serverFile.exists()){
            System.err.println("The file " + serverFile + " could not be located.");
        }
        else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(serverFile));
                while((line = br.readLine()) != null) {
                    out.println(line);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    //this method is for testing
//    protected boolean process(String command){
//        if(command.equalsIgnoreCase("DIR")){
//            System.out.println("dir");
//            return true;
//        }else if(command.equalsIgnoreCase("UPLOAD")){
//            System.out.println("upload");
////in.print(file contents) to the new file on server side
//            return true;
//        }else if(command.equalsIgnoreCase("DOWNLOAD")){
//            System.out.println("download");
////out.print(file contents) to the new file on the client side
//            return true;
//        }else{
//            out.println("Not a command");
//            return true;
//        }
//    }
}
