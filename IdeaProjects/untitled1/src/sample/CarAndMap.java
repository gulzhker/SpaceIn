package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class CarAndMap extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 700, 700);
       // Image img = ImageIO.read(new File("/Users/gulzan/Downloads/1510838932_mlechnyy-put.jpg"));

        stage.setScene(scene);
        stage.setTitle("JavaFX 2 Animations");
        stage.show();

        final Circle circle = new Circle(20, 20, 15);
        circle.setFill(Color.RED);
        //     Circle circle2 = new Circle(5, 5, 5);
        //    circle2.setFill(Color.DARKRED);
        //   Rectangle rec = new Rectangle(20,10);
        //    rec.setFill(Color.BROWN);

        Path path = new Path();
        //First line
        path.getElements().add(new MoveTo(20, 650));
        path.getElements().add(new LineTo(20,350));
        path.getElements().add(new LineTo(200, 350));
        path.getElements().add(new LineTo(200, 500));
        path.getElements().add(new ArcTo(100, 100, 0, 400, 500, false, false));
        path.getElements().add(new LineTo(400,300));
        path.getElements().add(new LineTo(150, 300));
        path.getElements().add(new ArcTo(120,120,0,150,60,true,true));
        path.getElements().add(new LineTo(400, 60));
        path.getElements().add(new LineTo(650, 60));
        path.getElements().add(new LineTo(650, 500));
        //Second Line
        path.getElements().add(new LineTo(600,500));
        path.getElements().add(new LineTo(600,110));
        path.getElements().add(new LineTo(150,110));
        path.getElements().add(new ArcTo(73, 73, 0, 150, 250, false, false));
        path.getElements().add(new LineTo(450,250));
        path.getElements().add(new LineTo(450,500));
        path.getElements().add(new ArcTo(140,140,0,150,500,false,true));
        path.getElements().add(new LineTo(150,390));
        path.getElements().add(new LineTo(70,390));
        path.getElements().add(new LineTo(70,650));
        path.getElements().add(new LineTo(20,650));
        path.setFill(Color.WHITE);

        Path carpath = new Path();
        carpath.getElements().add(new MoveTo(45, 650));
        carpath.getElements().add(new LineTo(45,370));
        carpath.getElements().add(new LineTo(175, 370));
        carpath.getElements().add(new LineTo(175, 500));
        carpath.getElements().add(new ArcTo(115, 115, 0, 425, 500, false, false));
        carpath.getElements().add(new LineTo(425,275));
        carpath.getElements().add(new LineTo(170, 275));
        carpath.getElements().add(new ArcTo(95,95,0,150,85,true,true));
        carpath.getElements().add(new LineTo(400, 85));
        carpath.getElements().add(new LineTo(625, 85));
        carpath.getElements().add(new LineTo(625, 500));


        //  path.setFill(Paint.valueOf("White"));
        scene.setFill(Paint.valueOf(String.valueOf(Color.GRAY)));
        //A label with the text element
        Label start = new Label("START");
        start.setLayoutX(20);
        start.setLayoutY(670);
        Label finish = new Label("FINISH");
        finish.setLayoutX(600);
        finish.setLayoutY(525);


        group.getChildren().add(path);
        group.getChildren().add(carpath);
        group.getChildren().add(circle);
        //   group.getChildren().add(circle2);
        group.getChildren().add(start);
        group.getChildren().add(finish);
        //  group.getChildren().add(rec);
        final PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(8.0));
        pathTransition.setDelay(Duration.seconds(0.5));
        pathTransition.setPath(carpath);
        pathTransition.setNode(circle);

        // pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        // pathTransition.setCycleCount(Timeline.INDEFINITE);
        //  pathTransition.setAutoReverse(true);
        pathTransition.play();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}