package simulation.forces;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import simulation.elements.masses.Mass;
import util.Vector;

/**
 * A resistive force that acts in the opposite direction of a mass's velocity.
 * 
 * @author Erick Gonzalez
 */
public class ViscousForce extends Force {
    private static final int TOGGLE_VISCOSITY_KEY = KeyEvent.VK_V;
    
    private double scaleValue;

    /**
     * Creates a viscous force with given strength.
     * 
     * @param scaleValue
     */
    public ViscousForce(double scaleValue) {
        this.scaleValue = scaleValue;
    }

    @Override
    public Vector getVectorRepresentation(Mass m, Dimension bounds) {
        Vector force = new Vector(m.getVelocity());
        force.negate();
        force.scale(scaleValue);
        return force;
    }

    @Override
    public void tryToggle(int key) {
        if (key == TOGGLE_VISCOSITY_KEY) {
            toggle();
        }
    }
}
