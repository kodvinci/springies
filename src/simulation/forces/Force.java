package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Vector;
/**
 * 
 * @author Leonard
 *
 */
public abstract class Force {
	
	
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
