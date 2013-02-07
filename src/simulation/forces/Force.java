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

	/**
	 * Default constructor. Does nothing.
	 */
	public Force() {

	}

	/**
	 * Gets the vector representation of this force that will be applied to some
	 * mass within some bounds provided.
	 * 
	 * @param m
	 * @param bounds
	 * @return
	 */
	public abstract Vector getVectorRepresentation(Mass m, Dimension bounds);

}
