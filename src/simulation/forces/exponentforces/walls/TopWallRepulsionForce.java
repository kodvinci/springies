package simulation.forces.exponentforces.walls;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Represents a repulsion force imposed by the top wall.
 * 
 * @author Erick Gonzalez
 */
public class TopWallRepulsionForce extends WallRepulsionForce {
    private static final int TOGGLE_TOP_WALL_KEY = KeyEvent.VK_1;
        
    public TopWallRepulsionForce(double magnitude, double exponent) {
        super(magnitude, exponent);
    }

    @Override
    public Vector getVectorRepresentation(Mass m, Dimension bounds) {
        double direction = Sprite.DOWN_DIRECTION;
        double distance = m.getY();

        return calculateExponentForce(m, bounds, direction, distance);
    }

    @Override
    public void tryToggle(int key) {
        if (key == TOGGLE_TOP_WALL_KEY) {
            toggle();
        }
    }
}
