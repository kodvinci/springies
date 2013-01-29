package simulation.elements.masses;

import java.awt.Dimension;

import util.Vector;

public class FixedMass extends Mass {

	public FixedMass(double x, double y, double mass) {
		super(x, y, mass);
	}

	@Override
	public void update(double elapsedTime, Dimension bounds) {
		
	}
	
	@Override
	public void applyForce(Vector force) {
		
	}
}
