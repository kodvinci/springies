package simulation.forces.exponentforces.walls;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents a repulsion force imposed by the left wall.
 * 
 * @author Erick Gonzalez
 */
public class LeftWallRepulsionForce extends WallRepulsionForce {
    private static final int TOGGLE_LEFT_WALL_KEY = KeyEvent.VK_4;
    
    public LeftWallRepulsionForce(double magnitude, double exponent) {
        super(magnitude, exponent);
    }

    @Override
    public Vector getVectorRepresentation(Mass m, Dimension bounds) {
        double direction = Sprite.RIGHT_DIRECTION;
        double distance = m.getX();

        return calculateExponentForce(m, bounds, direction, distance);
    }

    @Override
    public void tryToggle(int key) {
        if (key == TOGGLE_LEFT_WALL_KEY) {
            toggle();
        }
    }
}
