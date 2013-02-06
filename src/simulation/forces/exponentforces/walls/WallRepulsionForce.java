package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import simulation.forces.exponentforces.ExponentForce;
import util.Vector;

public abstract class WallRepulsionForce extends ExponentForce {

	public WallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public abstract Vector getVectorRepresentation(Mass m, Dimension bounds);
}
