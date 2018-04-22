package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.stage.Stage;
import metier.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map;

public class ControllerJeu implements Initializable{
    @FXML private Button quitterButton;
    @FXML private GridPane gpMap;

    static final String PATH_ACCUEIL = "/ihm/sample.fxml";
    static final String TITLE_ACCUEIL = "Accueil";

    private Block block;
    private Animation animation;

    public ControllerJeu() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animation = new Animation(this);
    }

    public void commencer(ActionEvent actionEvent) {
        animation.commencer();
    }

    public void addBlock(Block b){
        gpMap.add(b.getImageView(), b.getAbscisse(), b.getOrdonnee());
        showBlock(b);
    }
    public void showBlock(Block b){
        GridPane.setRowIndex(b.getImageView(), b.getOrdonnee());
        GridPane.setColumnIndex(b.getImageView(), b.getAbscisse());

        GridPane.setHalignment(b.getImageView(), HPos.CENTER);
        GridPane.setValignment(b.getImageView(), VPos.CENTER);
    }
    public void removeBlock(Block b){
        this.block = b;
        Platform.runLater(() -> {
            gpMap.getChildren().remove(block.getImageView());
        });
    }

    public void quitter(ActionEvent actionEvent) throws IOException {
        Stage fenetreAccueil = new Stage();

        Parent root1 = FXMLLoader.load(getClass().getResource(PATH_ACCUEIL));
        fenetreAccueil.setTitle(TITLE_ACCUEIL);
        fenetreAccueil.setScene(new Scene(root1));
        fenetreAccueil.show();

        ( (Stage) quitterButton.getScene().getWindow() ).close();
    }

    public void deplacer(KeyEvent keyEvent){
        switch (keyEvent.getCode()){
            case Q:
                animation.movePlayer(Deplacer.GAUCHE);
                break;
            case D:
                animation.movePlayer(Deplacer.DROITE);
                break;
            case Z:
                animation.addTir();
                break;
        }
    }
}