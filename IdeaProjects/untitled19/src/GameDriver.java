import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.net.URL;

public class GameDriver extends Application {
	MediaPlayer mp;

	public void start(Stage primaryStage) {
		URL resource = getClass().getResource("sprites/attacco.mp3");// add music mp3
		Media media = new Media(resource.toString());
		mp = new MediaPlayer(media);
//
		Pane pane = new Pane();
//
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mp.play();

		// Sets the pane's background color to black
		// If both pane and scene are not the same color, using buttons causes some graphical issues


		pane.setStyle("-fx-background: #000000;");

		Scene scene = new Scene(pane, 800, 750);
		new TitleScreen(pane);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
