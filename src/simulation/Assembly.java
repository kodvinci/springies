package simulation;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import simulation.elements.masses.Mass;
import simulation.elements.springs.Spring;
import simulation.forces.Force;
import view.Canvas;

/**
 * 
 * @author Leonard
 * @author Erick
 */

public class Assembly {
	private Canvas myView;
	// simulation state
	private List<Mass> myMasses;
	private List<Spring> mySprings;
	private List<Force> myForces;

	public Assembly(Canvas canvas) {
		myView = canvas;
		myMasses = new ArrayList<Mass>();
		mySprings = new ArrayList<Spring>();
		myForces = new ArrayList<Force>();
	}

	/**
	 * Draw all elements of the simulation.
	 * 
	 * @param pen
	 */
	public void paint(Graphics2D pen) {
		paintSprings(pen);
		paintMasses(pen);
		// make animation smoother
		Toolkit.getDefaultToolkit().sync();
		pen.dispose();
	}

	private void paintSprings(Graphics2D pen) {
		for (Spring s : mySprings) {
			s.paint(pen);
		}
	}

	private void paintMasses(Graphics2D pen) {
		for (Mass m : myMasses) {
			m.paint(pen);
		}
	}

	/**
	 * Updates state for springs and masses.
	 * 
	 * @param elapsedTime
	 *            time in milliseconds since last update
	 */
	public void update(double elapsedTime) {
		updateSprings(elapsedTime, myView.getSize());
		updateMasses(elapsedTime, myView.getSize());
	}

	private void updateSprings(double elapsedTime, Dimension bounds) {
		for (Spring s : mySprings) {
			s.update(elapsedTime, bounds);
		}
	}

	private void updateMasses(double elapsedTime, Dimension bounds) {
		for (Mass m : myMasses) {
			applyEnvironmentalForces(m, bounds);
			m.update(elapsedTime, bounds);
		}
	}

	private void applyEnvironmentalForces(Mass m, Dimension bounds) {
		for (Force f : myForces) {
			m.applyForce(f, bounds);
		}
	}

	/**
	 * Add given mass to this simulation.
	 * 
	 * @param m
	 */
	public void add(Mass m) {
		myMasses.add(m);
	}

	/**
	 * Add given spring to this simulation.
	 * 
	 * @param s
	 */
	public void add(Spring s) {
		mySprings.add(s);
	}

	/**
	 * Add given force to this simulation.
	 * 
	 * @param f
	 */
	public void add(Force f) {
		myForces.add(f);
	}
	public List<Mass> getMasses() {
		return myMasses;
	}
	public List<Spring> getSprings() {
		return mySprings;
	}

}
