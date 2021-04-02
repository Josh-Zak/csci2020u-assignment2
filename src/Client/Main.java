package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    static Controller controllerHandler;
    public static List<String> argsList;
    public static List<String> getArgs(){
        return argsList;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controllerHandler = loader.getController();
        controllerHandler.setArgs(getParameters().getRaw());
        argsList = controllerHandler.getArgs();
        controllerHandler.init();
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
