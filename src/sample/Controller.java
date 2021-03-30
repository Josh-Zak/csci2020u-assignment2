package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;



public class Controller {

//find out how to connect this to the client for the UI
    @FXML
    ListView local;
    @FXML
    ListView server;

    @FXML
    public void initialize() {
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        local.setItems(items);
        server.setItems(items);
    }
}
