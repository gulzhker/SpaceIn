package sample;

import javafx.scene.shape.Circle;

import java.util.Timer;
import java.util.TimerTask;

class Bullet extends Circle {

    private static double STEP = 2.0;
    private static int SPEED = 100;
    private static Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            move();
        }
    };

    Bullet(double centerX, double centerY) {
        super(centerX, centerY, 1.5);
       timer.schedule(task, SPEED);
    }


    private void move() {
        double oldY = this.getCenterY();
        this.setCenterY(oldY - STEP);
    }

}