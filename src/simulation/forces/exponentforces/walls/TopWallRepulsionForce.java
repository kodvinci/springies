package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.Mass;
import util.Sprite;
import util.Vector;

public class TopWallRepulsionForce extends WallRepulsionForce {

	public TopWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector force(Mass m, Dimension bounds) {
		double direction = Sprite.DOWN_DIRECTION;
		double distance = m.getY();
		
		return calculateExponentForce(m, bounds, direction, distance);
	}
}
