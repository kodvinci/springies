package simulation.elements.masses;

import java.awt.Dimension;

import simulation.forces.Force;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;

/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {
	// reasonable default values
	public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
	public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

	private double myMass;
	private Vector myAcceleration;

	/**
	 * XXX.
	 */
	public Mass(double x, double y, double mass) {
		super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
		myMass = mass;
		myAcceleration = new Vector();
	}

	/**
	 * XXX.
	 */
	@Override
	public void update(double elapsedTime, Dimension bounds) {
		applyForce(getBounce(bounds));
		// convert force back into Mover's velocity
		getVelocity().sum(myAcceleration);
		myAcceleration.reset();
		// move mass by velocity
		super.update(elapsedTime, bounds);
	}

	public void applyForce(Force f, Dimension bounds) {
		applyForce(f.getVectorRepresentation(this, bounds));
	}

	/**
	 * Use the given force to change this mass's acceleration.
	 */
	public void applyForce(Vector force) {
		myAcceleration.sum(force);
	}

	/**
	 * Convenience method.
	 */
	public double distance(Mass other) {
		// this is a little awkward, so hide it
		return new Location(getX(), getY()).distance(new Location(other.getX(),
				other.getY()));
	}

	// check for move out of bounds
	private Vector getBounce(Dimension bounds) {
		final double IMPULSE_MAGNITUDE = 2;
		Vector impulse = new Vector();
		if (getLeft() < 0) {
			impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
		} else if (getRight() > bounds.width) {
			impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
		}
		if (getTop() < 0) {
			impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
		} else if (getBottom() > bounds.height) {
			impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
		}
		impulse.scale(getVelocity().getRelativeMagnitude(impulse));
		return impulse;
	}

	public double getMass() {
		return myMass;
	}
}
