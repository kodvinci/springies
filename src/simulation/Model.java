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
 * Handles the state and updates for the Springies simulation.
 * 
 * @author Erick Gonzalez
 */
public class Model {
	// bounds and input for game
	private Canvas myView;
	// simulation state
	private List<Mass> myMasses;
	private List<Spring> mySprings;
	private List<Force> myForces;

	/**
	 * Create a game of the given size with the given display for its shapes.
	 */
	public Model(Canvas canvas) {
		myView = canvas;
		myMasses = new ArrayList<Mass>();
		mySprings = new ArrayList<Spring>();
		myForces = new ArrayList<Force>();
	}

	/**
	 * Draw all elements of the simulation.
	 */
	public void paint(Graphics2D pen) {
		paintSprings(pen);
		paintMasses(pen);

		// TODO: This smooths things out
		Toolkit.getDefaultToolkit().sync();
		pen.dispose();
	}

	/**
	 * Draws all springs of the simulation.
	 */
	public void paintSprings(Graphics2D pen) {
		for (Spring s : mySprings) {
			s.paint(pen);
		}
	}

	/**
	 * Draws all masses of the simulation.
	 */
	public void paintMasses(Graphics2D pen) {
		for (Mass m : myMasses) {
			m.paint(pen);
		}
	}

	/**
	 * Update simulation for this moment, given the time since the last moment.
	 */
	public void update(double elapsedTime) {
		updateSprings(elapsedTime, myView.getSize());
		updateMasses(elapsedTime, myView.getSize());
	}

	/**
	 * Update springs in the simulation.
	 */
	public void updateSprings(double elapsedTime, Dimension bounds) {
		for (Spring s : mySprings) {
			s.update(elapsedTime, bounds);
		}
	}

	/**
	 * Update masses in the simulation.
	 */
	public void updateMasses(double elapsedTime, Dimension bounds) {
		for (Mass m : myMasses) {
			applyEnvironmentalForces(m, bounds);
			m.update(elapsedTime, bounds);
		}
	}

	/**
	 * Applies all forces in the environment to a given mass.
	 */
	public void applyEnvironmentalForces(Mass m, Dimension bounds) {
		for (Force f : myForces) {
			m.applyForce(f, bounds);
		}
	}

	/**
	 * Add given mass to this simulation.
	 */
	public void add(Mass mass) {
		myMasses.add(mass);
	}

	/**
	 * Add given spring to this simulation.
	 */
	public void add(Spring spring) {
		mySprings.add(spring);
	}

	/**
	 * Add given force to this simulation.
	 */
	public void add(Force f) {
		myForces.add(f);
	}
}
