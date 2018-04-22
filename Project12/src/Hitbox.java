import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Hitbox {

	private Rectangle boundaries;
	private Sprite sprite;
	private Pane pane;

	 //Constructs a hitbox object and ties it to a sprite
	 //The width of the hitbox
	 //the height of the hitbox
	 //the sprite that the hitbox will be attached to
	 //The pane the hitbox will be displayed in

	public Hitbox(double width,double height, Sprite sprite, Pane pane) {

		// Create a default hitbox with given dimensions at 0,0
		// Hitbox location will be bound in specific sprite class
		boundaries = new Rectangle(0, 0, width, height);
		this.sprite = sprite;
	}

	// Handles collision checking between this and other hitboxes
	 //The sprite that a collision is being checked with

	protected void checkCollision(Sprite sprite) {
		// Checks to see if any point within the given sprite's hitbox is within this hitbox
		if (boundaries.intersects(sprite.getHitbox().getBoundaries().getBoundsInLocal()))
		{
			// Calls the onCollide methods in both sprites
			this.sprite.onCollide(sprite);
			sprite.onCollide(this.sprite);
		}
	}


	 //Returns the rectangle that forms the bounds of the hitbox
	 //the bounding rectangle

	protected Rectangle getBoundaries() {
		return boundaries;
	}


	 //Returns the pane to display the hitbox and sprites in
	 //return the display pane

	protected Pane getPane() {
		return pane;
	}

	//Sets the bounding rectangle of the hitbox
	//boundaries the ractangle to be the bounds

	protected void setBoundaries(Rectangle boundaries) {
		this.boundaries = boundaries;
	}
}
