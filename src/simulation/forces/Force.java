package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Vector;

public abstract class Force {
	
	
	public Force() {
		
	}
	
	public abstract Vector force(Mass m, Dimension bounds);
}
