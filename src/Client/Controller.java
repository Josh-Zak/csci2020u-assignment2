package Client;

import Server.ServerThread;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Controller {
    @FXML
    ListView local;
    @FXML
    ListView server;

    public List<String> argument;


    public void setArgs(List<String> arguments) {
        argument = arguments;
    }
    public List<String> getArgs() {
        return argument;
    }
    Client fileShareClient = null;

    @FXML
    public void init() throws IOException {
        String[] clientList = ServerThread.clientFolder("./src/" + argument.get(2));
        String[] serverList = ServerThread.handleDir();
        ObservableList<String> items1 = FXCollections.observableArrayList (
                clientList);
        ObservableList<String> items2 = FXCollections.observableArrayList (
                serverList);
        server.setItems(items2);
        local.setItems(items1);
        fileShareClient = new Client();
        setArgs(Main.getArgs());
//        ServerThread.handleDownload(serverList[0]);
        System.out.println(argument);


    }
    //Download button: create a new file in the client shared folder(clientFile)
    // read the selected file from the server shared folder(serverFile)
    // copy the text to the clientFile
    // delete the file in the server shared folder

    // alternative solution: get the path to the server shared folder and get the path to client shared folder
    // Files.move(source, target)
    // not sure if it could work when connecting to the server
    @FXML
    public void downloadAction(ActionEvent event) throws IOException{
//figure out how to implement this into the serverHandler
        String selected = server.getSelectionModel().getSelectedItem().toString();
        String downloadMessage = "DOWNLOAD " + selected + " " + argument.get(2);
        fileShareClient.send(downloadMessage);
        local.getItems().add(selected);
        fileShareClient.listen();
    }

    //
    @FXML
    public void uploadAction(ActionEvent event) throws IOException{
//figure out how to implement this into the serverHandler
        String selected = local.getSelectionModel().getSelectedItem().toString();
        String uploadMessage = "UPLOAD " + selected + " " + argument.get(2);
        fileShareClient.send(uploadMessage);
        server.getItems().add(selected);
        fileShareClient.listen();
    }
}
