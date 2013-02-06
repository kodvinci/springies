package simulation.elements.masses;

import util.Vector;

/**
 * Model class for a fixed mass element.
 * 
 * @author Erick Gonzalez
 */
public class FixedMass extends Mass {

	public FixedMass(double x, double y, double mass) {
		super(x, y, mass);
	}

	@Override
	public void applyForce(Vector force) {
		// do nothing when force is applied
	}
}
