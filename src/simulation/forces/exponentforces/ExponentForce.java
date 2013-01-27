package simulation.forces.exponentforces;

import java.awt.Dimension;

import simulation.elements.Mass;
import simulation.forces.Force;
import util.Vector;

public abstract class ExponentForce extends Force {
	private double myMagnitude;
	private double myExponent;
	
	public ExponentForce(double magnitude, double exponent) {
		myMagnitude = magnitude;
		myExponent = exponent;
	}
	
	protected Vector calculateExponentForce(Mass m, Dimension bounds, double direction, double distance) {
		if (myExponent == 2.0) {
			double magnitude = myMagnitude / (distance * distance);
			return new Vector(direction, magnitude); 
		} else if (myExponent == 0.0) {
			return new Vector(direction, myMagnitude);
		}
		return null;
	}
	
	@Override
	public abstract Vector force(Mass m, Dimension bounds);
}
