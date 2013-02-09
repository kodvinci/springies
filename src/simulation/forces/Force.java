package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Vector;

/**
 * Represents a Force.
 * 
 * @author Erick Gonzalez
 */
public abstract class Force {
    private boolean myVisiblity;

    /**
     * Turns the force on
     */
    public Force() {
        myVisiblity = true;
    }

    /**
     * 
     * @return true if force is present
     */
    public boolean isOn() {
        return myVisiblity;
    }

    protected void toggle() {
        myVisiblity = !myVisiblity;
    }

    /**
     * Gets the vector representation of this force that will be applied to some mass within some
     * bounds provided.
     * 
     * @param m
     *            a mass the force is applied to
     * @param bounds
     *            the bounds within which the force is present
     * @return
     */
    public abstract Vector getVectorRepresentation(Mass m, Dimension bounds);

    /**
     * Tries to toggle the force on or off if the given key matches the force's toggle key
     * 
     * @param key the given key pressed
     */
    public abstract void tryToggle(int key);
}
