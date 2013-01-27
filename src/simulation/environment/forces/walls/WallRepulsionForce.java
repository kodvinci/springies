package simulation.environment.forces.walls;

import java.awt.Dimension;

import simulation.elements.Mass;
import simulation.environment.forces.Force;
import util.Vector;

public abstract class WallRepulsionForce extends Force {	

	public WallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	} 
	
	@Override
	public abstract Vector force(Mass m, Dimension bounds);
}
