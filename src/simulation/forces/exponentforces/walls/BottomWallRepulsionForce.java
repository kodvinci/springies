package simulation.forces.exponentforces.walls;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents a repulsion force imposed by the bottom wall.
 * 
 * @author Erick Gonzalez
 */
public class BottomWallRepulsionForce extends WallRepulsionForce {

	public BottomWallRepulsionForce(double magnitude, double exponent) {
		super(magnitude, exponent);
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		double direction = Sprite.UP_DIRECTION;
		double distance = bounds.getHeight() - m.getY();

		return calculateExponentForce(m, bounds, direction, distance);
	}
}
