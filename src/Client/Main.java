package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    static Controller controllerHandler;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controllerHandler = (Controller) loader.getController();
        controllerHandler.setArgs(getParameters().getRaw());
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
//        List<String> list1 = getParameters().getRaw();
//        System.out.println(list1.get(0));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
