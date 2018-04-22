package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAccueil{
    @FXML
    private Label lbText;

    @FXML
    private GridPane gpAccueil;

    public void quitter(ActionEvent actionEvent) {
        Stage fenetreAccueil = (Stage) gpAccueil.getScene().getWindow();
        fenetreAccueil.close();
    }

    @FXML
    public void Jouer(ActionEvent actionEvent){
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/ihm/Jeu.fxml"));
            Stage fenetreJeu = new Stage();
            fenetreJeu.setTitle("Jeu");
            fenetreJeu.setScene(new Scene(root1));
            fenetreJeu.show();
        } catch (IOException e) {
            e.printStackTrace();
            lbText.setText("Erreur de chargement du Jeu");
        }
        quitter(actionEvent);
    }
}