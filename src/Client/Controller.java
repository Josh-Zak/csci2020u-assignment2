package Client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

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
//    @FXML
//    public void initialize() {
//        ListView<String> list = new ListView<String>();
//        ObservableList<String> items = FXCollections.observableArrayList (
//                "Single", "Double", "Suite", "Family App");
//        local.setItems(items);
//        server.setItems(items);
//        fileShareClient = new Client();
//        setArgs(Main.getArgs());
//        System.out.println(argument);
//
//    }

    @FXML
    public void init(){
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        local.setItems(items);
        server.setItems(items);
        fileShareClient = new Client();
        setArgs(Main.getArgs());
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
    public void downloadAction(ActionEvent event){
//figure out how to implement this into the serverHandler
        fileShareClient.send("download");

    }

    //
    @FXML
    public void uploadAction(ActionEvent event){
//figure out how to implement this into the serverHandler
        fileShareClient.send("upload");
    }
}
