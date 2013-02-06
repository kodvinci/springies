package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

public class LeftWallRepulsionForce extends WallRepulsionForce {

	public LeftWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector force(Mass m, Dimension bounds) {
		double direction = Sprite.RIGHT_DIRECTION;
		double distance = m.getX();

		return calculateExponentForce(m, bounds, direction, distance);
	}
}
