package simulation;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import simulation.elements.masses.Mass;
import simulation.elements.springs.Spring;
import simulation.forces.Force;
import util.Location;
import view.Canvas;


/**
 * Checks for user keyboard presses and mouse clicks and mouse drags
 * 
 * @author Leonard
 * @author Erick
 * 
 */

public class UserInputLister {

    // keys
    private static final int NEW_ASSEMBLY_KEY = KeyEvent.VK_N;
    private static final int CLEAR_ASSEMBLIES_KEY = KeyEvent.VK_C;
    private static final int INCREASE_AREA_KEY = KeyEvent.VK_UP;
    private static final int DECREASE_AREA_KEY = KeyEvent.VK_DOWN;

    // reasonable default values
    private static final double DEFAULT_DRAG_SPRING_CONSTANT = 0.05;
    private static final int BOUNDS_DELTA = 10;

    private Canvas myView;
    private List<Mass> myMasses;
    private List<Force> myForces;
    private List<Spring> mySprings;

    private int myToggleDelay = 0;
    private Spring myDragSpring;
    private Mass myMousePositionMass;

    /**
     * 
     * @param canvas
     * @param masses
     * @param forces
     * @param springs
     */
    public UserInputLister (Canvas canvas,
                            List<Mass> masses,
                            List<Force> forces,
                            List<Spring> springs) {
        myView = canvas;
        myMasses = masses;
        myForces = forces;
        mySprings = springs;
    }

    /**
     * 
     * @param elapsedTime
     * @param bounds
     */
    public void checkKeyboardInput (double elapsedTime, Dimension bounds) {
        int key = myView.getLastKeyPressed();
        tryTogglingForces(key);
        tryChangingState(key);
    }

    /**
     * 
     * @param key
     */
    private void tryTogglingForces (int key) {
        if (myToggleDelay == 0) {
            for (Force f : myForces) {
                f.tryToggle(key);
            }
            myToggleDelay = 1;
        }
        else {
            --myToggleDelay;
        }
    }

    /**
     * 
     * @param key
     */
    private void tryChangingState (int key) {
        switch (key) {
            case NEW_ASSEMBLY_KEY:
                myView.resetLastKeyPressed();
                createNewAssembly();
                break;
            case CLEAR_ASSEMBLIES_KEY:
                myView.resetLastKeyPressed();
                clearAssemblies();
                break;
            case INCREASE_AREA_KEY:
                changeSizeOfView(-BOUNDS_DELTA, BOUNDS_DELTA);
                break;
            case DECREASE_AREA_KEY:
                changeSizeOfView(BOUNDS_DELTA, -BOUNDS_DELTA);
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @param originDelta
     * @param rectangleDelta
     */
    private void changeSizeOfView (int originDelta, int rectangleDelta) {
        myView.setBounds(myView.getX() + originDelta, myView.getY() + originDelta,
                         myView.getWidth() + 2 * rectangleDelta, myView.getHeight() + 2 *
                                                                 rectangleDelta);
    }

    /**
     * 
     * @param elapsedTime
     * @param bounds
     */
    public void checkMouseInput (double elapsedTime, Dimension bounds) {
        Point mousePosition = myView.getLastMousePosition();
        if (mousePosition == Canvas.NO_MOUSE_PRESSED) {
            myDragSpring = null;
            myMousePositionMass = null;
        }
        else if (myDragSpring != null) {
            myMousePositionMass.setCenter(new Location(mousePosition.getX(), mousePosition.getY()));
            myDragSpring.update(elapsedTime, bounds);
        }
        else {
            myDragSpring = getDragSpring(mousePosition);
        }
    }

    /**
     * 
     * @param mousePosition
     * @return
     *          A new spring between the current clicked mouse position, 
     *and the closest mass to that mouse position.
     *          
     */
    private Spring getDragSpring (Point mousePosition) {
        Mass closestMass = getClosestMass(mousePosition);
        myMousePositionMass = new Mass(mousePosition.getX(), mousePosition.getY(), 0);
        return new Spring(closestMass, myMousePositionMass,
                          closestMass.distance(myMousePositionMass), DEFAULT_DRAG_SPRING_CONSTANT);
    }

    /**
     * @param mousePosition
     *          last clicked mouse position
     * @return
     *          the closest mass to the last clicked mouse position.
     */
    private Mass getClosestMass (Point mousePosition) {
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
     * Clears all the assemblies
     */
    private void clearAssemblies () {
        mySprings.clear();
        myMasses.clear();
    }

    /**
     * creates a new assembly
     */
    private void createNewAssembly () {
        myView.loadEntities();
    }

    public Spring getMyDragSpring () {
        return myDragSpring;
    }
    public Mass getMyMousePositionMass () {
        return myMousePositionMass;
    }
    
}
