package sample;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

    public class Animation_GUI extends Application {

        public Button resize, rotate, translate, shear;
        public RadioButton rectangle, polygon, line;
        public TextField angle, numOfLines, x0, y0, x, y, new_width, new_height, translate_x, translate_y, durationOfRotation, durationOfScaling, durationOfTranslation, shear_x, shear_y;
        public Scene scene;
        public Button addRotation, addScaling, addTranslation;
        StackPane shapes;
        // this group holds needed shapes
        Group group1;
        // shapes

        Rectangle rctn;
        Polygon polygonShape;
        Line lineShape;
        @Override
        public void start(Stage primaryStage) throws Exception {
            shapes = new StackPane();
            final ToggleGroup group = new ToggleGroup();
            rectangle = new RadioButton("Rectangle");
            rectangle.getStyleClass().add("buttons");
            rectangle.setPrefWidth(190);
            rectangle.setToggleGroup(group);
            rectangle.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    rctn  = new Rectangle();
                    rctn.setX(90);
                    rctn.setY(75);
                    rctn.setWidth(100);
                    rctn.setHeight(80);
                    rctn.setFill(Color.AQUA);
                    group1 = new Group(rctn);
                    shapes.getChildren().add(group1);
                    rctn.setTranslateX(400);

                }
            });
            numOfLines = new TextField();
            numOfLines.setPromptText("type how many lines you need");

            polygon = new RadioButton("Polygon");
            polygon.getStyleClass().add("buttons");
            polygon.setPrefWidth(190);
            polygon.setToggleGroup(group);
            polygon.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    polygonShape  = new Polygon();
                    polygonShape.setFill(Color.AQUA);
                    if(Integer.parseInt(numOfLines.getText()) == 3) {
                        polygonShape.getPoints().addAll(300.0, 150.0,
                                400.0, 450.0,
                                600.0, 150.0);
                    }
                    if(Integer.parseInt(numOfLines.getText()) == 4) {
                        polygonShape.getPoints().addAll(500.0, 150.0,
                                800.0, 150.0,
                                750.0, 250.0,
                                600.0, 250.0);
                    }

                    group1 = new Group(polygonShape);
                    shapes.getChildren().add(group1);
                }
            });

            HBox firstParameters = new HBox();
            x0 = new TextField();
            x0.setPromptText("x0");
            x0.setMaxWidth(85);

            y0 = new TextField();
            y0.setPromptText("y0");
            y0.setMaxWidth(85);
            firstParameters.getChildren().addAll(x0, y0);
            firstParameters.setSpacing(10);

            HBox finalParameters = new HBox();
            x = new TextField();
            x.setPromptText("x");
            x.setMaxWidth(85);

            y = new TextField();
            y.setPromptText("y");
            y.setMaxWidth(85);
            finalParameters.getChildren().addAll(x, y);
            finalParameters.setSpacing(10);

            line = new RadioButton("Line");
            line.getStyleClass().add("buttons");
            line.setPrefWidth(190);
            line.setToggleGroup(group);
            line.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    lineShape = new Line();
                    lineShape.setStartX(Double.parseDouble(x0.getText()));
                    lineShape.setStartY(Double.parseDouble(y0.getText()));
                    lineShape.setEndX((Double.parseDouble(x.getText())));
                    lineShape.setEndY((Double.parseDouble(y.getText())));
                    group1 = new Group(lineShape);
                    shapes.getChildren().add(group1);
                }
            });

            HBox newSize = new HBox();
            new_width = new TextField();
            new_width.setPromptText("new width");
            new_width.setMaxWidth(88);

            new_height = new TextField();
            new_height.setPromptText("new height");
            new_height.setMaxWidth(92);
            newSize.getChildren().addAll(new_width, new_height);
            newSize.setSpacing(2);

            resize = new Button("Scaling");
            resize.getStyleClass().add("buttons");
            resize.setPrefWidth(190);
            resize.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Scale scale = new Scale();
                    scale.setX(Double.parseDouble(new_width.getText()));
                    scale.setY(Double.parseDouble(new_height.getText()));
                    scale.setPivotX(100);
                    scale.setPivotY(100);
                    group1.getTransforms().add(scale);
                }
            });
            addScaling = new Button("Add animation");
            addScaling.getStyleClass().add("buttons");
            addScaling.setPrefWidth(190);
            addScaling.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //creating Scale Transition
                    ScaleTransition scaleTransition = new ScaleTransition();
                    // setting the duration of scaling
                    scaleTransition.setDuration(Duration.millis(Double.parseDouble(durationOfScaling.getText())*1000));
                    //seting the Node for transition
                    scaleTransition.setNode(group1);
                    //setting the dimension for scaling
                    scaleTransition.setByX(Double.parseDouble(new_width.getText()));
                    scaleTransition.setByX(Double.parseDouble(new_height.getText()));
                    //setting the cycle count
                    scaleTransition.setCycleCount(5);
                    //setting the auto reverse to value
                    scaleTransition.setAutoReverse(true);
                    //playing the animation
                    scaleTransition.play();

                }
            });

            durationOfScaling = new TextField();
            durationOfScaling.setPromptText("Animation duration (sek)");

            rotate = new Button("Rotation");
            rotate.getStyleClass().add("buttons");
            rotate.setPrefWidth(190);
            rotate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Rotate rotate = new Rotate();
                    rotate.setAngle(Double.parseDouble(angle.getText()));
                    rotate.setPivotX(50);
                    rotate.setPivotY(50);
                    group1.getTransforms().addAll(rotate);
                }
            });

            addRotation = new Button("Add animation");
            addRotation.getStyleClass().add("buttons");
            addRotation.setPrefWidth(190);
            addRotation.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //creating Scale Transition
                    RotateTransition rotateTransition = new RotateTransition();
                    // setting the duration of rotation
                    rotateTransition.setDuration(Duration.millis(Double.parseDouble(durationOfRotation.getText())*1000));
                    //seting the Node for transition
                    rotateTransition.setNode(group1);
                    //setting the dimension for scaling
                    rotateTransition.setByAngle(Double.parseDouble(angle.getText()));
                    //setting the cycle count
                    rotateTransition.setCycleCount(5);
                    //setting the auto reverse to value
                    rotateTransition.setAutoReverse(true);
                    //playing the animation
                    rotateTransition.play();
                }
            });
            durationOfRotation = new TextField();
            durationOfRotation.setPromptText("Animation duration (sek)");

            angle = new TextField();
            angle.setPromptText("rotate to (type an angle value)");

            translate_x = new TextField();
            translate_x.setPromptText("move to by X");
            translate_y = new TextField();
            translate_y.setPromptText("move to by Y");
            translate = new Button("Translation");
            translate.getStyleClass().add("buttons");
            translate.setPrefWidth(190);
            translate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Translate translate = new Translate();
                    translate.setX(Double.parseDouble(translate_x.getText()));
                    translate.setY(Double.parseDouble(translate_y.getText()));
                    group1.getTransforms().addAll(translate);
                }
            });

            addTranslation = new Button("Add animation");
            addTranslation.getStyleClass().add("buttons");
            addTranslation.setPrefWidth(190);
            addTranslation.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //creating Scale Transition
                    TranslateTransition translateTransition = new TranslateTransition();
                    // setting the duration of scaling
                    translateTransition.setDuration(Duration.millis(Double.parseDouble(durationOfTranslation.getText())*1000));
                    //seting the Node for transition
                    translateTransition.setNode(group1);
                    //setting the dimension for scaling
                    translateTransition.setByX(Double.parseDouble(translate_x.getText()));
                    translateTransition.setByX(Double.parseDouble(translate_y.getText()));
                    //setting the cycle count
                    translateTransition.setCycleCount(5);
                    //setting the auto reverse to value
                    translateTransition.setAutoReverse(true);
                    //playing the animation
                    translateTransition.play();
                }
            });

            durationOfTranslation = new TextField();
            durationOfTranslation.setPromptText("Animation duration (sek)");

            shear = new Button("Shearing");
            shear.getStyleClass().add("buttons");
            shear.setPrefWidth(190);
            shear.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //Creating shear transformation
                    Shear shear = new Shear();

                    //Setting the pivot points
                    shear.setPivotX(200);
                    shear.setPivotY(250);

                    //Setting the dimensions for the shear
                    shear.setX(Double.parseDouble(shear_x.getText()));
                    shear.setY(Double.parseDouble(shear_y.getText()));

                    //Adding the transformation to the polygon
                    group1.getTransforms().addAll(shear);
                }
            });

            shear_x = new TextField();
            shear_x.setPromptText("shear X");
            shear_x.setMaxWidth(190);

            shear_y = new TextField();
            shear_y.setPromptText("shear Y");
            shear_y.setMaxWidth(190);

            GridPane buttons = new GridPane();
            buttons.add(rectangle, 0, 1 );
            buttons.add(polygon, 0, 2);
            buttons.add(numOfLines, 0, 3);
            buttons.add(line, 0, 4);
            buttons.add(firstParameters, 0, 5);
            buttons.add(finalParameters, 0, 6);
            buttons.add(rotate, 1, 1);
            buttons.add(addRotation, 1, 2);
            buttons.add(durationOfRotation, 1, 3);
            buttons.add(angle, 1, 4);

            buttons.add(resize, 2, 1);
            buttons.add(addScaling, 2, 2);
            buttons.add(durationOfScaling, 2, 3);
            buttons.add(newSize, 2, 4);

            buttons.add(translate, 3, 1);
            buttons.add(addTranslation, 3, 2);
            buttons.add(translate_x, 3, 3);
            buttons.add(translate_y, 3, 4);

            buttons.add(shear, 4, 1);
            buttons.add(shear_x, 4, 2);
            buttons.add(shear_y, 4, 3);

            buttons.setHgap(15);
            buttons.setVgap(15);

            VBox vbox = new VBox();
            vbox.getChildren().add(buttons);
            vbox.getChildren().add(shapes);
            scene = new Scene(vbox, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("Animation_GUI_Style.css").toString());
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main (String [] args) {
            Application.launch(args);
        }
    }
