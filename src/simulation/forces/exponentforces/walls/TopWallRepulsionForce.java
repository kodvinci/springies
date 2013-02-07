package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents a repulsion force imposed by the top wall.
 * 
 * @author Erick Gonzalez
 */
public class TopWallRepulsionForce extends WallRepulsionForce {

	public TopWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		double direction = Sprite.DOWN_DIRECTION;
		double distance = m.getY();

		return calculateExponentForce(m, bounds, direction, distance);
	}
}
