package sample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application implements Measures {

    public int record = 0;
    Bullet bulletc;
    //mp
    MediaPlayer mp;
    Pane pane = new Pane();
    //add image
  //  Image backV = new Image("sample/resources/back.jpg");
    //using
  //  ImageView back = new ImageView(backV);
    //add image
    Image shipV = new Image("sample/resources/ship.png");
    //using
    ImageView ship = new ImageView(shipV);
    //add
    Image alienV = new Image("sample/resources/invasore1.gif");
    //use
    Image alienVI = new Image("sample/resources/invasore2.gif");

    //creating rectangle
    static Rectangle pointer = new Rectangle();
    String statusMP;

    //col and row for aliens
    ImageView[] aliens = new ImageView[ALIEN_COLUMN * ALIEN_ROW];
bggg
    public int MOV = 0;

    boolean rightAlien = true;
    //boolean bulletIsAlive = false;
    boolean newLevel = true;

    int score = 0;
    int updateTime = 28;

    Text punt = new Text("Score: " + score);

    String sRecord = " ";
    String n;
//this class creating aliens row and col
    public void again() {
        for (int j = 0; j < ALIEN_ROW; j++) {
            for (int i = 0; i < ALIEN_COLUMN; i++) {
                aliens[j * ALIEN_COLUMN + i] = new ImageView(alienV);
                //preserve image ratio
                aliens[j * ALIEN_COLUMN + i].setPreserveRatio(true);
                // x ,y position of aliens
                aliens[j * ALIEN_COLUMN + i].setX(i * 50);
                aliens[j * ALIEN_COLUMN + i].setY(j * 50);
                //width of my aliens
                aliens[j * ALIEN_COLUMN + i].setFitWidth(ALIEN_EDGE);
                //showing aliens
               pane.getChildren().add(aliens[j * ALIEN_COLUMN + i]);
                if (i == ALIEN_COLUMN - 1 && j == 0) {
                    pointer.setWidth(ALIEN_EDGE);
                    pointer.setHeight(ALIEN_EDGE);
                    pointer.setFill(Color.TRANSPARENT);
                    pointer.setX(aliens[i].getX() + ALIEN_EDGE);
                }
            }
        }
        updateTime-=3;
    }

    @Override
    public void start(Stage primaryStage) {

        URL resource = getClass().getResource("resources/attacco.mp3");// add music mp3
        Media media = new Media(resource.toString());
        mp = new MediaPlayer(media);

        // ship image preserve ratio
        ship.setPreserveRatio(true);
        ship.setFitWidth(80);
        ship.setX(100);
        ship.setY(490);

        //pane.getChildren().add(back);
        pane.getChildren().add(ship);
        pane.getChildren().add(punt);

        punt.setFont(Font.font("Verdana", 20));
        punt.setFill(Color.YELLOW);

        again();

       // back.setX(0);
      //  back.setY(0);

        punt.setX(10);
        punt.setY(20);

        Duration dI = new Duration(updateTime);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        Timeline tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.setOnKeyPressed(e -> keyboardManage(e));

        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.play();

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(scene);
        primaryStage.show();

        String k = System.getProperty("user.home");
        n = k + File.separator + "si.txt";
        System.out.println(n);

        try {
            FileReader fr = new FileReader(n);
            BufferedReader br = new BufferedReader(fr);
            sRecord = br.readLine();
            record = Integer.parseInt(sRecord);
            System.out.println("" + record);
            br.close();
            fr.close();
        } catch (Exception e) {
            record = 0;
        }
    }

    public void keyboardManage(KeyEvent ke) {
        if (ke.getCode() == KeyCode.D) {
            double x = ship.getX();
            x += 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.A) {
            double x = ship.getX();
            x -= 10;
            ship.setX(x);
        } else if (ke.getCode() == KeyCode.SPACE) { // shoot
            bulletc = new Bullet(10, 50, ship.getX(), aliens, pane);
        }
    }

    public void movementCore() {
        if (rightAlien) { //check if the enemy is going toward right
            if (pointer.getX() + ALIEN_EDGE >= SCREEN_WIDTH) { //check collision on right edge
                rightAlien = false;
                for (int i = 0; i < aliens.length; i++) {
                    if (aliens[i] != null) {
                        aliens[i].setY(aliens[i].getY() + 50);
                    }
                }
            }

            for (int i = 0; i < aliens.length; i++) {
                if (aliens[i] != null) {
                    aliens[i].setX(aliens[i].getX() + SPEED); //move the enemy
                }
            }
            pointer.setX(pointer.getX() + SPEED); //move the pointer
        } else {
            if (pointer.getX() - ((ALIEN_EDGE * (ALIEN_COLUMN + 2))) <= 0) {
                rightAlien = true;
                for (int i = 0; i < aliens.length; i++) {
                    if (aliens[i] != null) {
                        aliens[i].setY(aliens[i].getY() + 50);
                    }
                }
            }
            for (int i = 0; i < aliens.length; i++) {
                if (aliens[i] != null) {
                    aliens[i].setX(aliens[i].getX() - SPEED);
                }
            }
            pointer.setX(pointer.getX() - SPEED);
        }
        MOV++; //animation block
        if (MOV == 20) { //first frame
            for (int j = 0; j < aliens.length; j++) {
                if (aliens[j] != null) {
                    aliens[j].setImage(alienVI);
                }
            }
        } else if (MOV == 40) { //second frame
            for (int j = 0; j < aliens.length; j++) {
                if (aliens[j] != null) {
                    aliens[j].setImage(alienV);
                }
            }
            MOV = 0;
        }
        if (bulletc != null) {
            score += bulletc.getScore();
            punt.setText("Score: " + score);
        }
        if (score % 50 * 49 == 0 && score >= 50 * 49) {
            if (newLevel) {
                again();
                newLevel = false;
            }
        }
        if (score % 50 * 49 > 0 && score > 50 * 49) {
            newLevel = true;
        }
    }


        @Override
    public void stop(){
        try {
            FileWriter fw = new FileWriter(n);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(""+score);
            bw.close();
            fw.close();
        } catch (Exception e) {
            record = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}