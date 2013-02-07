package simulation.forces.exponentforces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import simulation.forces.Force;
import util.Vector;

/**
 * An exponent force is one that varies according to a provided exponent. If the
 * exponent is 2.0, the magnitude of the force varies inversely with distance.
 * Otherwise, the magnitude is constant.
 * 
 * @author Erick Gonzalez
 */
public abstract class ExponentForce extends Force {
	private double myMagnitude;
	private double myExponent;

	/**
	 * Creates a new exponent force with given magnitude and exponent
	 * 
	 * @param magnitude
	 * @param exponent
	 */
	public ExponentForce(double magnitude, double exponent) {
		myMagnitude = magnitude;
		myExponent = exponent;
	}

	protected Vector calculateExponentForce(Mass m, Dimension bounds,
			double direction, double distance) {
		double magnitude = myMagnitude / Math.pow(distance, myExponent);
		return new Vector(direction, magnitude);
	}

	@Override
	public abstract Vector getVectorRepresentation(Mass m, Dimension bounds);
}
