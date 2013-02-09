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
    private boolean isOn;    

    /**
     * Turns the force on
     */
    public Force() {
        isOn = true;
    }
    
    public boolean isOn() {
        return isOn;
    }

    protected void toggle() {
        isOn = !isOn;
    }    
    
    
    /**
     * Gets the vector representation of this force that will be applied to some mass within some
     * bounds provided.
     * 
     * @param m
     * @param bounds
     * @return
     */
    public abstract Vector getVectorRepresentation(Mass m, Dimension bounds);
        
    public abstract void tryToggle(int key);
}
