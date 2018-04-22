package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static final String PATH = "/ihm/Jeu.fxml";
    static final String TITLE = "Menu";
    @Override
    public void start(Stage primaryStage) throws Exception{
        try{

            Parent root = FXMLLoader.load(getClass().getResource(PATH));
            primaryStage.setTitle(TITLE);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
