package simulation.elements.masses;

import java.awt.Dimension;

import simulation.forces.Force;
import util.Vector;

/**
 * Model class for a fixed mass element. A fixed mass element does not move in
 * reaction to a force
 * 
 * @author Erick Gonzalez
 */
public class FixedMass extends Mass {

	/**
	 * Create a fixed mass at a given position.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param mass
	 */
	public FixedMass(double x, double y, double mass) {
		super(x, y, -mass); // mass is initially read in as negative, so you
							// must negate it so the center of mass force works
							// as intended.
	}

	@Override
	public void applyForce(Force f, Dimension bounds) {
		// do nothing when force is applied
	}

	@Override
	public void applyForce(Vector v) {
		// do nothing when force is applied
	}

}
