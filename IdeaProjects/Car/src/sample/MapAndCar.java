package sample;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapAndCar extends Application {
    @Override
    public void start(Stage primaryStage) {
        // for my moving car i have used just circle
        final Circle circle = new Circle(30, 40, 20);
        //giving the color
        circle.setFill(Color.GOLDENROD);

//giving the path to the road
        //The PathElement class represents an abstract element of the Path that can represent
        // any geometric objects like straight lines, arcs, quadratic curves, cubic curves, etc.
        PathElement[] path = {
                        new MoveTo(0, 300),
                        new ArcTo(100, 100, 0, 100, 400, false, false),
                        new LineTo(300, 400),
                        new ArcTo(100, 100, 0, 400, 300, false, false),
                        new LineTo(400, 100),
                        new ArcTo(100, 100, 0, 300, 0, false, false),
                        new LineTo(100, 0),
                        new ArcTo(100, 100, 0, 0, 100, false, false),
                        new LineTo(0, 300),
                        new ClosePath()
                };

        //building and giving the color for the road
        Path road = new Path();
        road.setStroke(Color.BLACK);
        road.setStrokeWidth(70);
        road.getElements().addAll(path);

         //divider to the road
        Path divider = new Path();
        divider.setStroke(Color.WHITE);
        divider.setStrokeWidth(5);
        divider.getStrokeDashArray().addAll(10.0, 10.0);
        divider.getElements().addAll(path);

        //This Transition creates a path animation that spans its duration.
        // The translation along the path is done by updating the translateX and translateY variables of the node,
        // and the rotate variable will get updated if orientation is set to OrientationType.ORTHOGONAL_TO_TANGENT, at regular interval.
        PathTransition anim = new PathTransition();
        anim.setNode(circle);
        anim.setPath(road);
        anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim.setInterpolator(Interpolator.LINEAR);
        anim.setDuration(new Duration(3000));
        anim.setCycleCount(Timeline.INDEFINITE);

//state applied to a Group will be applied to all children of that group
        Group root = new Group();
        root.getChildren().addAll(road, divider, circle);
        //move the all on the center of my program
        root.setTranslateX(50);
        root.setTranslateY(50);

        //creating the action for my car when you click it's runs
        root.setOnMouseClicked(me -> {
            Animation.Status status = anim.getStatus();
            if (status == Animation.Status.RUNNING &&
                    status != Animation.Status.PAUSED)
                anim.pause();
            else
                anim.play();
        });

        Scene scene = new Scene(root, 500, 500 , Color.CHOCOLATE);
       // layout.setStyle("-fx-background-image; -fx-padding: 10;");
        primaryStage.setTitle("Car");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
