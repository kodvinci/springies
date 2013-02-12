package simulation.forces;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import simulation.elements.masses.Mass;
import util.Vector;


/**
 * A force representing gravity.
 * 
 * @author Erick Gonzalez
 */
public class GravitationalForce extends Force {
    private static final int TOGGLE_GRAVITY_KEY = KeyEvent.VK_G;

    private Vector myAcceleration;

    /**
     * Creates a gravitational force with specified magnitude and direction.
     * 
     * @param angle the direction of the gravitational force
     * @param magnitude the magnitude of the gravitational force
     */
    public GravitationalForce (double angle, double magnitude) {
        myAcceleration = new Vector(angle, magnitude);
    }

    @Override
    public Vector getVectorRepresentation (Mass m, Dimension bounds) {
        Vector forceOfGravity = new Vector(myAcceleration);
        forceOfGravity.scale(m.getMass());
        return forceOfGravity;
    }

    @Override
    public void tryToggle (int key) {
        if (key == TOGGLE_GRAVITY_KEY) {
            toggle();
        }
    }
}
