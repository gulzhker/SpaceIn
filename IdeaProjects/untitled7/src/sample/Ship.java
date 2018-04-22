package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ship extends Group {

    private static Color GUN_COLOR = Color.web("#2B3133");

    private Rectangle gun;
    private Rectangle cocpit;

    private final double rectangleSpeed = 400.0;
    private final double minX = -300;
    private final double maxX = 275;
    private final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
    private final LongProperty lastUpdateTime = new SimpleLongProperty();


    public Ship(double x, double y) {
        cocpit = new Rectangle(x, y, 25, 12);
        buildShip();
        setMovement();
        setAnimation();
        this.setFocusTraversable(true);

        this.getChildren().addAll(cocpit, gun);
    }


    private void setAnimation() {
        AnimationTimer rectangleAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime.get() > 0) {
                    final double elapsedSeconds = (now - lastUpdateTime.get()) / 1_000_000_000.0;
                    final double deltaX = elapsedSeconds * rectangleVelocity.get();
                    final double oldX = Ship.this.getTranslateX();
                    final double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
                    Ship.this.setTranslateX(newX);
                }
                lastUpdateTime.set(now);
            }
        };
        rectangleAnimation.start();
    }

    private void buildShip() {
        gun = new Rectangle();
        gun.setX(cocpit.getX() + 10.5);
        gun.setY(cocpit.getY() - 5);
        gun.setWidth(4);
        gun.setHeight(5);
        gun.setFill(GUN_COLOR);
    }

    private void setMovement() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
    }

    public Bullet shoot() {
//        return new Bullet(this.getTranslateY(), this.getTranslateX());
        return null;
    }

    public void goLeft() {
        rectangleVelocity.set(-rectangleSpeed);
    }

    public void goRight() {
        rectangleVelocity.set(rectangleSpeed);
    }

    public void stop() {
        rectangleVelocity.set(0);
    }
}