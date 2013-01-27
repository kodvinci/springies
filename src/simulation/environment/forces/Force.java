package simulation.environment.forces;

import java.awt.Dimension;

import simulation.elements.Mass;
import util.Vector;

public abstract class Force {
	private double myMagnitude;
	private double myExponent;
	
	public Force() {
		
	}
	
	public Force(double magnitude, double exponent) {
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
	
	public abstract Vector force(Mass m, Dimension bounds);
}
