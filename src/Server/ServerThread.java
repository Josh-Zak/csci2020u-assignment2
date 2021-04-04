package Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
    protected Socket socket = null;
    protected static PrintWriter out = null;
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

    public void run(){
        out.println("Connected to Server"); // print the message to the client
        String line = null;
        try {
            while((line = in.readLine()) != null){
                process(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        try{
            out.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
// handle the request from the client in this function
    protected void process(String message) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(message);
        String command = tokenizer.nextToken(); //first token of the message, usually the command
        String file = tokenizer.nextToken();
        String args = null;
        if(tokenizer.hasMoreTokens()){
            // if the message has more than 2 arguments, pass from the second word in the message as args
            args = message.substring(command.length() + file.length() + 1, message.length());
        }
        if(null != args) {
            process(command, file, args);
        }else{
            System.err.println("No arguments given");
        }

    }

    protected void process(String command, String file, String args) throws IOException {
        if(command.equalsIgnoreCase("DIR")){
            out.println("dir");
            String[] fileName = handleDir();
        }else if(command.equalsIgnoreCase("UPLOAD")){
            File baseDir = new File("./src/test");
            handleUpload(baseDir,"text.txt");
            out.println("upload shared");
            handleUpload(baseDir,file);
//in.print(file contents) to the new file on server side
        }else if(command.equalsIgnoreCase("DOWNLOAD")){
            handleDownload(file);
//out.print(file contents) to the new file on the client side
        }else{
            out.println("Not a command");
        }
    }
    //TODO:
// create a function to load the contents of the shared folder in the client
// which is that handleDir is doing at the moment
    public static String[] clientFolder(String folderPath) {
        File file = new File(folderPath);
        if(!file.exists()){
            System.err.println("The folder doesn't exist. Please try again.");
        }
        return file.list();
    }
// create 3 functions to handle the commands from the client

    // Returns a listing of the contents of the shared folder in the client (filename in the client folder)
    // and load it to the server shared folder
    public static String[] handleDir() throws IOException {
        Files.createDirectories(Paths.get("./src/shared"));
        File serverFolder = new File("./src/shared");
        if(serverFolder.mkdir()){
            System.out.println("New folder created");
        }
        String[] serverFiles = serverFolder.list();
        for(int i=0;i<serverFiles.length;i++){
            System.out.println(serverFiles[i]);
        }
        return serverFiles;
    }
    // functionality of upload: we have the parameter as the path to the shared folder of the local client
    // create a new shared folder for the server if not created
    // read the text from the selected file from the client folder (fileName)
    // create a new file in the server folder (copyFile) and write all of the texts from fileName to copyFile
    public void handleUpload(File clientDir,String fileName) {
        File clientFile = new File(clientDir, fileName);
//ADDED FILENAME SO WE CAN WRITE TO IT
        File serverFile = new File("./src/shared", fileName);

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
    // print out the file to the client
    public void handleDownload(String fileName){
        File serverFile = new File("./src/shared",fileName);
        File clientFile = new File("./src/test", fileName);
        String line = "";
        if(!serverFile.exists()){
            System.err.println("The file " + serverFile + " could not be located.");
        }
        else {
            try {
                FileWriter fw = new FileWriter(clientFile);
                BufferedReader br = new BufferedReader(new FileReader(serverFile));
                while((line = br.readLine()) != null) {
                    fw.write(line);
                    System.out.println(line);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}
