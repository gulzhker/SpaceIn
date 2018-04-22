import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

        @FXML
        private ImageView image;

        @FXML
        private ImageView im1;

        @FXML
        private Button butt;

        @FXML
        private ImageView im2;

        @FXML
        void imagee(ActionEvent event) {
        }

        @FXML
        void button1(ActionEvent event) throws IOException {
            System.out.println("Press Play");
            Parent homePage = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene homescene = new Scene(homePage);
            Stage appStage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.setScene(homescene);
            appStage.show();
        }
        @FXML
        void image1(ActionEvent event) {

        }

        @FXML
        void image2(ActionEvent event) {
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

