package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents a repulsion force imposed by the left wall.
 * 
 * @author Erick Gonzalez
 */
public class LeftWallRepulsionForce extends WallRepulsionForce {

	public LeftWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		double direction = Sprite.RIGHT_DIRECTION;
		double distance = m.getX();

		return calculateExponentForce(m, bounds, direction, distance);
	}
}
