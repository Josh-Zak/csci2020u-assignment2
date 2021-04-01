package Server;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class ServerHandler implements Runnable {

    private Socket socket = null;
    private BufferedReader requestInput = null;
    private DataOutputStream responseOutput = null;
    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String line = null;
        try {
            line = requestInput.readLine();
            handleDIR(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                requestInput.close();
                responseOutput.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    public void handleDIR(String request) throws IOException {
        try {
            StringTokenizer tokenizer = new StringTokenizer(request);
            String command = tokenizer.nextToken();
            String uri = tokenizer.nextToken();
            if(command.equalsIgnoreCase("DIR")){
                File baseDir = new File("www");
            }else{

            }
        }catch(NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
