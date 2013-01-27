package simulation.forces;

import java.awt.Dimension;

import simulation.elements.Mass;
import util.Vector;

public class GravitationalForce extends Force {
	private Vector acceleration;

	public GravitationalForce(double angle, double magnitude) {
		acceleration = new Vector(angle, magnitude);
	}

	@Override
	public Vector force(Mass m, Dimension bounds) {
		Vector forceOfGravity = new Vector(acceleration);
		forceOfGravity.scale(m.getMass());
		return forceOfGravity;
	}
}
