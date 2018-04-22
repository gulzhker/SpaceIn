package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

    public class House extends Application {
        public void start(Stage stage) {

            //building roof
            SVGPath roof = new SVGPath();
            //roof1.setStrokeWidth(5);

            roof.setContent("M150 100 L65 200 L235 200 Z");
            roof.setStroke(Color.BLACK);
            roof.setStrokeWidth(10);
            roof.setFill(Paint.valueOf("White"));

            //wall
            SVGPath wall = new SVGPath();

            wall.setContent("M75 200 L75 300 L225 300 L225 200 Z");
            wall.setStroke(Color.BLACK);
            wall.setStrokeWidth(10);
            wall.setFill(Paint.valueOf("White"));

            //window
            SVGPath window = new SVGPath();
            window.setContent("M110 220 L110 250 L150 250 L150 220 Z");
            window.setStroke(Color.BLACK);
            window.setStrokeWidth(5);
            window.setFill(Paint.valueOf("RED"));

            //door
            SVGPath door = new SVGPath();
            door.setContent("M170 270 L170 300 L190 300 L190 270 Z");
            door.setStroke(Color.BLACK);
            door.setStrokeWidth(5);
            door.setFill(Paint.valueOf("White"));

            Group root = new Group(roof, wall, window,door);
            Scene scene = new Scene(root, 300, 350);
            scene.setFill(Paint.valueOf("#FFF5EE"));
            stage.setScene(scene);
            stage.setTitle("House");
            stage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
}
