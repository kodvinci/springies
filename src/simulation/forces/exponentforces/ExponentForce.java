package simulation.forces.exponentforces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import simulation.forces.Force;
import util.Vector;

public abstract class ExponentForce extends Force {
	private double myMagnitude;
	private double myExponent;

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
