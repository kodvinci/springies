package simulation.forces;

import java.awt.Dimension;

import simulation.elements.Mass;
import util.Vector;

public class ViscousForce extends Force {
	private double scaleValue;

	public ViscousForce(double scaleValue) {
		this.scaleValue = scaleValue;
	}

	@Override
	public Vector force(Mass m, Dimension bounds) {
		Vector force = new Vector(m.getVelocity());
		force.negate();
		force.scale(scaleValue);
		return force;
	}
}
