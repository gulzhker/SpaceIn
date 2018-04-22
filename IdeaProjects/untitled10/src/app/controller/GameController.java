package app.controller;

import app.model.Ship;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    @FXML
    private Pane playground;
    @FXML
    public ToolBar headerToolBar;
    @FXML
    private Button closeButton;

    private Ship ship;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setExitTooltip();
        setShip();
        this.closeButton.setFocusTraversable(false);
    }

    private void setShip() {
        this.ship = new Ship(300, 370);
        setShipMovement();
        ship.setFocusTraversable(true);
        this.playground.getChildren().add(ship);
    }

    private void setExitTooltip() {
        Tooltip tooltip = new Tooltip("Press to close game");
        tooltip.setFont(Font.font(12));
        this.closeButton.setTooltip(tooltip);
    }

    private void setShipMovement() {
        ship.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                ship.goLeft();
            }
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                ship.goRight();
            }
            if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.W) {
                playground.getChildren().add(ship.shoot());
            }
        });

        ship.setOnKeyReleased((KeyEvent event) -> {
            if (shouldStop(event)) {
                ship.stop();
            }
        });
    }

    private boolean shouldStop(KeyEvent event) {
        return event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.A || event.getCode() == KeyCode.D;
    }

    @FXML
    public void exit() {
        System.exit(0);
    }


}
