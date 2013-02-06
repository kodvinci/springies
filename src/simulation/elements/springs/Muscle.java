package simulation.elements.springs;

import java.awt.Dimension;

import simulation.elements.masses.Mass;

/**
 * This element connects two mass elements and provide movement by contracting
 * and expanding the spring's rest length in a harmonic motion. Length of muscle
 * ranges from 0 to twice the original length
 * @author Leonard  
 */
public class Muscle extends Spring {
	//default values
	private double myBeta; //Takes on values between 0 and 1. Fixes wave amplitude
	private static final double OMEGA = 30; //Speed of oscillation of muscles
	private static final double PHI = Math.PI/2; //Can take on the values 0, Math.PI/2, Math.PI/3, 3*Math.PI/4 
	private static final double ALPHA = 1; // Assumes values between 1 and 0. Relative amplitude

	private double restLength;

	public Muscle(Mass start, Mass end, double length, double kVal, double amplitude) {
		super(start, end, length, kVal);
		restLength = length;
		myBeta = amplitude;
	}

	@Override
	public void update(double elapsedTime, Dimension bounds) {
		setMyLength(restLength
				* (1 + ALPHA * myBeta * Math.sin(OMEGA + PHI)));
		super.update(elapsedTime, bounds); 
	}

}
