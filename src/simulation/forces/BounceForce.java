package simulation.forces;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Sprite;
import util.Vector;

public class BounceForce extends Force {

	private double myImpulseMagnitude;

	public BounceForce(double impulseMagnitude) {
		myImpulseMagnitude = impulseMagnitude;
	}

	@Override
	public Vector getVectorRepresentation(Mass m, Dimension bounds) {
		Vector impulse = new Vector();
		if (m.getLeft() < 0) {
			impulse = new Vector(Sprite.RIGHT_DIRECTION, myImpulseMagnitude);
		} else if (m.getRight() > bounds.width) {
			impulse = new Vector(Sprite.LEFT_DIRECTION, myImpulseMagnitude);
		}
		if (m.getTop() < 0) {
			impulse = new Vector(Sprite.DOWN_DIRECTION, myImpulseMagnitude);
		} else if (m.getBottom() > bounds.height) {
			impulse = new Vector(Sprite.UP_DIRECTION, myImpulseMagnitude);
		}
		impulse.scale(m.getVelocity().getRelativeMagnitude(impulse));
		return impulse;
	}

}
