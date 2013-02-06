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
	 * 
	 * @param m
	 * @param bounds
	 * @return
	 */
	public abstract Vector force(Mass m, Dimension bounds);
}
