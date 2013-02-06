package simulation.elements.springs;

import java.awt.Dimension;

import simulation.elements.masses.Mass;

/**
 * This element connects two mass elements and provide movement by contracting
 * and expanding the spring's rest length in a harmonic motion. Length of muscle
 * ranges from 0 to twice the original length
 * 
 * @author Leonard
 * @author Erick
 * 
 */
public class Muscle extends Spring {
	// default values
	private static final double OMEGA = 30; // Speed of oscillation of muscles
	private static final double PHI = Math.PI / 2; // Can take on 0, Math.PI/2,
													// Math.PI/3,
													// 3*Math.PI/4 radians
	private static final double ALPHA = 1; // Assumes values between 1 and 0.
											// relative
											// amplitude

	private double restLength;
	private double myAmplitude; // Takes on values between 0 and 1. Fixes wave
								// amplitude

	public Muscle(Mass start, Mass end, double length, double kVal,
			double amplitude) {
		super(start, end, length, kVal);
		restLength = length;
		myAmplitude = amplitude;
	}

	@Override
	public void update(double elapsedTime, Dimension bounds) {
		setMyLength(restLength
				* (1 + ALPHA * myAmplitude * Math.sin(OMEGA + PHI)));
		super.update(elapsedTime, bounds);
	}

}
