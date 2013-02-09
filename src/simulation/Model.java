package simulation;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import simulation.elements.masses.Mass;
import simulation.elements.springs.Spring;
import view.Canvas;

/**
 * 
 * @author Leonard
 *
 */
public class Model {
	// bounds and input for game
	private Canvas myView;
	// simulation state
	private List<Assembly> myAssemblies;
	private static final int NEW_ASSEMBLY_KEY = KeyEvent.VK_N;	
	private static final int CLEAR_ASSEMBLIES_KEY = KeyEvent.VK_C;	

	/**
	 * Create a game of the given size with the given display for its shapes.
	 * 
	 * @param canvas
	 */
	public Model(Canvas canvas) {
		myView = canvas;
		myAssemblies = new ArrayList<Assembly>();
	}

	/**
	 * Draw all elements of the simulation.
	 * 
	 * @param pen
	 */
	public void paint(Graphics2D pen) {
		for (Assembly a : myAssemblies) {
			for (Mass m: a.getMasses()){
				m.paint(pen);
			}
			for (Spring s: a.getSprings()){
				s.paint(pen);
			}
		}
		// make animation smoother
		Toolkit.getDefaultToolkit().sync();
		pen.dispose();
	}
	
	/**
	 * Updates assemblies
	 * 
	 * @param elapsedTime
	 *            time in milliseconds since last update
	 */
	public void update(double elapsedTime) {
		for (Assembly a : myAssemblies) {
			a.update(elapsedTime);
		}
		
		int key = myView.getLastKeyPressed();
		if(key == NEW_ASSEMBLY_KEY) {
			myView.resetLastKeyPressed();
			newAssembly();
		}
		if (key == CLEAR_ASSEMBLIES_KEY) {
			myView.resetLastKeyPressed();
			clearAssemblies();
		}
	}

	public void addAssembly(Assembly assembly) {
		myAssemblies.add(assembly);
	}
	
	private void clearAssemblies() {
		myAssemblies.removeAll(myAssemblies);
	}
	
	private void newAssembly() {
		myView.loadModel();
	}
	
	public Canvas getMyView(){
		return myView;
	}
}
