package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import simulation.elements.masses.Mass;
import simulation.elements.springs.Spring;
import simulation.forces.Force;
import util.Location;
import view.Canvas;

/**
 * 
 * @author Leonard
 * @author Erick
 */
public class Model {

    // reasonable default values
    private static final double DEFAULT_DRAG_SPRING_CONSTANT = 0.05;

    // keys
    private static final int NEW_ASSEMBLY_KEY = KeyEvent.VK_N;	
	private static final int CLEAR_ASSEMBLIES_KEY = KeyEvent.VK_C;
	private static final int INCREASE_AREA_KEY = KeyEvent.VK_UP;
	private static final int DECREASE_AREA_KEY = KeyEvent.VK_DOWN;

    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private List<Force> myForces;
    private Spring myDragSpring;
    private Mass mousePositionMass;

    /**
     * Create a game of the given size with the given display for its shapes.
     * 
     * @param canvas
     *            the canvas
     */
    public Model(Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
    }

    /**
     * Draw all elements of the simulation.
     * 
     * @param pen
     *            a pen that draws
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
        if (myDragSpring != null) {
            myDragSpring.paint(pen);
        }
        if (mousePositionMass != null) {
            mousePositionMass.paint(pen);
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
        Dimension bounds = myView.getSize();
        checkUserInput(elapsedTime, bounds);
        updateSprings(elapsedTime, bounds);
        updateMasses(elapsedTime, bounds);
    }
    
    private void checkUserInput(double elapsedTime, Dimension bounds) {                
        checkKeyboardInput(elapsedTime, bounds);
        checkMouseInput(elapsedTime, bounds);
    }
    
    private void checkKeyboardInput(double elapsedTime, Dimension bounds) {
        int key = myView.getLastKeyPressed();
        switch(key) {
            case NEW_ASSEMBLY_KEY:
                myView.resetLastKeyPressed();
                createNewAssembly();
                break;
            case CLEAR_ASSEMBLIES_KEY:
                myView.resetLastKeyPressed();
                clearAssemblies();
                break;
            case INCREASE_AREA_KEY:
                myView.setBounds(myView.getX()-10, myView.getY()-10, myView.getWidth()+20, myView.getHeight()+20);
                break;
            case DECREASE_AREA_KEY:
                myView.setBounds(myView.getX()+10, myView.getY()+10, myView.getWidth()-20, myView.getHeight()-20);
                break;
        }        
    }
    
    private void checkMouseInput(double elapsedTime, Dimension bounds) {
        Point mousePosition = myView.getLastMousePosition();
        if (mousePosition == Canvas.NO_MOUSE_PRESSED) {
            myDragSpring = null;
            mousePositionMass = null;
        }
        else if (myDragSpring != null) {
            mousePositionMass.setCenter(new Location(mousePosition.getX(), mousePosition.getY()));
            myDragSpring.update(elapsedTime, bounds);
        }
        else {
            myDragSpring = getDragSpring(mousePosition);
        }
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

    /*
     * Creates a new spring between the current clicked mouse position, and the closest mass to that
     * mouse position.
     */
    private Spring getDragSpring(Point mousePosition) {
        Mass closestMass = getClosestMass(mousePosition);
        mousePositionMass = new Mass(mousePosition.getX(), mousePosition.getY(), 0);
        return new Spring(closestMass, mousePositionMass, closestMass.distance(mousePositionMass),
                DEFAULT_DRAG_SPRING_CONSTANT);
    }

    /*
     * Retrieves the closest mass to the last clicked mouse position.
     */
    private Mass getClosestMass(Point mousePosition) {
        Mass closestMass = null;
        double smallestDistance = Double.MAX_VALUE;

        for (Mass m : myMasses) {
            Location massLocation = new Location(m.getX(), m.getY());
            double currentDistance = massLocation.distance(mousePosition);
            if (currentDistance < smallestDistance) {
                smallestDistance = currentDistance;
                closestMass = m;
            }
        }
        return closestMass;
    }

    /**
     * Add given mass to this simulation.
     * 
     * @param m
     *            mass
     */
    public void add(Mass m) {
        myMasses.add(m);
    }

    /**
     * Add given spring to this simulation.
     * 
     * @param s
     *            spring
     */
    public void add(Spring s) {
        mySprings.add(s);
    }

    /**
     * Add given force to this simulation.
     * 
     * @param f
     *            force
     */
    public void add(Force f) {
        myForces.add(f);
    }

    private void clearAssemblies() {
        mySprings.clear();
        myMasses.clear();
    }

    private void createNewAssembly() {
        myView.loadEntities();
    }

}
