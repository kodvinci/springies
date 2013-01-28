package simulation.elements.springs;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Vector;

/**
 * This element connects two mass elements and provide movement by 
 * contracting and expanding the spring's rest length in a harmonic motion.
 * Length of muscle ranges from 0 to twice the original length
 * 
 * @author Leonard
 *
 */
public class Muscle extends Spring {
	//default values
	private double BETA; //Takes on values between 0 and 1. Fixes wave amplitude
	private double OMEGA = 30; //Speed of oscillation of muscles
	private double PHI = Math.PI/2; //Can take on 0, Math.PI/2, Math.PI/3, 3*Math.PI/4 radians
	private double ALPHA = 1; // Assumes values between 1 and 0. relative amplitude
	
	private double restLength;
	
	public Muscle(Mass start, Mass end, double length, double kVal, double amplitude)
	{
		super(start, end, length, kVal);		
		restLength = length;
		BETA = amplitude;
	}
	
	@Override
	public void update(double elapsedTime, Dimension bounds)
	{
		setMyLength(restLength * (1 + ALPHA*BETA*Math.sin(OMEGA + PHI)));
		
		double dx = myStart().getX() - myEnd().getX();
		double dy = myStart().getY() - myEnd().getY();
		
		Vector force = new Vector(Vector.angleBetween(dx, dy), myK()
				* (myLength() - Vector.distanceBetween(dx, dy)));
		
		myStart().applyForce(force);
		force.negate();
		myEnd().applyForce(force);
		
		// update sprite values based on attached masses
		setCenter(getCenter(myStart(), myEnd()));
		setSize(getSize(myStart(), myEnd()));
		setVelocity(Vector.angleBetween(dx, dy), 0);
	}
	
}
