package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

    /**
     * This class to write the simulation of racing cars
     */

    public class RacingCar extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {


            HBox firstHBox = new HBox();
            firstHBox.setSpacing(8);
            firstHBox.setAlignment(Pos.CENTER);

            VBox secondVBox = new VBox(firstHBox);
            Timeline[] racelines = new Timeline[2];
            TextField[] tfCarRate = new TextField[2];

            for (int i = 0; i < racelines.length; i++) {

                /**
                 * Drawing the Panel of the Car
                 */

                MyCarPane carPane = new MyCarPane(1000, 200);
                racelines[i] = new Timeline(new KeyFrame(Duration.millis(10), e -> carPane.carMoving(1)));
                racelines[i].setCycleCount(carPane.getCycleTime());
                racelines[i].play();

                carPane.setStyle("-fx-border-color: black;" + "-fx-border-insets: 5px");
                secondVBox.getChildren().add(carPane);

                /**
                 * The main panel to contain cars and racing
                 */
                tfCarRate[i] = new TextField();
                //tfCarRate[i].setPrefColumnCount(3);
                //tfCarRate[i].setText(racelines[i].getCurrentRate() +"");
                Label lblCarRates = new Label("Car No. " + (i + 1) + ":", tfCarRate[i]);
                lblCarRates.setContentDisplay(ContentDisplay.RIGHT);
                firstHBox.getChildren().add(lblCarRates);

                /**
                 * Listener for car
                 */
                final int index = i;
                racelines[i].setOnFinished(e -> {
                    carPane.reset();
                    racelines[index].play();
                });

                /**
                 * Listen on change of car speed
                 */
                tfCarRate[i].setOnAction(e -> {
                    tfCarRate[index].setText(
                            Math.min(Double.parseDouble(tfCarRate[index].getText()), 100) + "");
                    racelines[index].setRate(
                            Double.parseDouble(tfCarRate[index].getText()));

                });
                tfCarRate[i].setFocusTraversable(false);
            }

            primaryStage.setScene(new Scene(secondVBox));
            primaryStage.setTitle("Car Racing");
            primaryStage.show();
        }

        private class MyCarPane extends Pane {
                }
            }
        }
    }
