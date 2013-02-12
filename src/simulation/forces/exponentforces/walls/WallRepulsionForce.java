package simulation.forces.exponentforces.walls;

import java.awt.Dimension;
import simulation.elements.masses.Mass;
import simulation.forces.exponentforces.ExponentForce;
import util.Vector;


/**
 * Abstract representation of wall repulsion force.
 * 
 * @author Erick Gonzalez
 */
public abstract class WallRepulsionForce extends ExponentForce {

    /**
     * Abstract representation of a wall repulsion force
     * 
     * @param magnitude magnitude of the wall repulsion force
     * @param exponent exponent value
     */
    public WallRepulsionForce (double magnitude, double exponent) {
        super(magnitude, exponent);
    }

    @Override
    public abstract Vector getVectorRepresentation (Mass m, Dimension bounds);
}
