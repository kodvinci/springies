package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Vector;

/**
 * A force representing gravity.
 * 
 * @author Erick Gonzalez
 */
public class GravitationalForce extends Force {
	private Vector acceleration;

	/**
	 * Creates a gravitational force with specified magnitude and direction.
	 * 
	 * @param angle
	 * @param magnitude
	 */
	public GravitationalForce(double angle, double magnitude) {
		acceleration = new Vector(angle, magnitude);
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		Vector forceOfGravity = new Vector(acceleration);
		forceOfGravity.scale(m.getMass());
		return forceOfGravity;
	}
}
