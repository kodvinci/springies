package simulation.forces.exponentforces.walls;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;


/**
 * Represents repulsion force imposed by the right wall.
 * 
 * @author Erick Gonzalez
 */
public class RightWallRepulsionForce extends WallRepulsionForce {
    private static final int TOGGLE_RIGHT_WALL_KEY = KeyEvent.VK_2;

    /**
     * Creates a repulsion force coming from the right wall
     * 
     * @param magnitude magnitude of this repulsion force
     * @param exponent exponent value
     */
    public RightWallRepulsionForce (double magnitude, double exponent) {
        super(magnitude, exponent);
    }

    @Override
    public Vector getVectorRepresentation (Mass m, Dimension bounds) {
        double direction = Sprite.LEFT_DIRECTION;
        double distance = bounds.getWidth() - m.getX();

        return calculateExponentForce(m, bounds, direction, distance);
    }

    @Override
    public void tryToggle (int key) {
        if (key == TOGGLE_RIGHT_WALL_KEY) {
            toggle();
        }
    }
}
