package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * A force that is responsible for bouncing masses off a wall that they come into contact with.
 * 
 * @author Erick Gonzalez
 */
public class BounceForce extends Force {

    private double myImpulseMagnitude;

    /**
     * Creates bounce force with given magnitude.
     * 
     * @param impulseMagnitude
     */
    public BounceForce(double impulseMagnitude) {
        myImpulseMagnitude = impulseMagnitude;
    }

    @Override
    public Vector getVectorRepresentation(Mass m, Dimension bounds) {
        Vector impulse = new Vector();
        if (m.getLeft() < 0) {
            impulse = new Vector(Sprite.RIGHT_DIRECTION, myImpulseMagnitude);
        }
        else if (m.getRight() > bounds.width) {
            impulse = new Vector(Sprite.LEFT_DIRECTION, myImpulseMagnitude);
        }
        if (m.getTop() < 0) {
            impulse = new Vector(Sprite.DOWN_DIRECTION, myImpulseMagnitude);
        }
        else if (m.getBottom() > bounds.height) {
            impulse = new Vector(Sprite.UP_DIRECTION, myImpulseMagnitude);
        }
        impulse.scale(m.getVelocity().getRelativeMagnitude(impulse));
        return impulse;
    }

    @Override
    public void tryToggle(int key) {
        // do nothing, bounce force is never toggled on or off
    }    
}
