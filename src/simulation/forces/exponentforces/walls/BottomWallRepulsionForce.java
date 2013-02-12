package simulation.forces.exponentforces.walls;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;


/**
 * Represents a repulsion force imposed by the bottom wall.
 * 
 * @author Erick Gonzalez
 */
public class BottomWallRepulsionForce extends WallRepulsionForce {
    private static final int TOGGLE_BOTTOM_WALL_KEY = KeyEvent.VK_3;

    /**
     * Creates a repulsion force from the bottom wall
     * 
     * @param magnitude the magnitude of this repulsion force
     * @param exponent exponent value
     */
    public BottomWallRepulsionForce (double magnitude, double exponent) {
        super(magnitude, exponent);
    }

    @Override
    public Vector getVectorRepresentation (Mass m, Dimension bounds) {
        double direction = Sprite.UP_DIRECTION;
        double distance = bounds.getHeight() - m.getY();

        return calculateExponentForce(m, bounds, direction, distance);
    }

    @Override
    public void tryToggle (int key) {
        if (key == TOGGLE_BOTTOM_WALL_KEY) {
            toggle();
        }
    }
}
