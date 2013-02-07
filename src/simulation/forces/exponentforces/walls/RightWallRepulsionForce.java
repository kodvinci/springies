package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents repulsion force imposed by the right wall.
 * 
 * @author Erick Gonzalez
 */
public class RightWallRepulsionForce extends WallRepulsionForce {

	public RightWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		double direction = Sprite.LEFT_DIRECTION;
		double distance = bounds.getWidth() - m.getX();

		return calculateExponentForce(m, bounds, direction, distance);
	}
}
