package simulation.elements.masses;

import java.awt.Dimension;
import simulation.forces.BounceForce;
import simulation.forces.Force;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * A class that represents a mass on the canvas.
 * 
 * @author Robert C. Duvall
 * @author Erick Gonzalez
 */
public class Mass extends Sprite {
    // reasonable default values
    /**
     * Default size of a mass.
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    /**
     * Default image for a mass.
     */
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");
    /**
     * Default magnitude with which masses bounce off walls.
     */
    public static final double DEFAULT_WALL_BOUNCE_MAGNITUDE = 2.0;

    private double myMass;
    private Vector myAcceleration;

    /**
     * Create a mass at the given location
     * 
     * @param x
     *        x-coordinate
     * @param y
     *        y-coordinate
     * @param mass
     *        mass
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyForce(new BounceForce(DEFAULT_WALL_BOUNCE_MAGNITUDE), bounds);
        // convert force back into Mover's velocity
        getVelocity().sum(myAcceleration);
        myAcceleration.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }

    /**
     * Apply a force to this mass with the given bounds of the canvas where this force will be
     * present.
     * 
     * @param f
     *        force
     * @param bounds
     *        bounds of the canvas where the force is present
     */
    public void applyForce (Force f, Dimension bounds) {
        applyForce(f.getVectorRepresentation(this, bounds));
    }

    /**
     * Apply the vector representation of a force to this mass.
     * 
     * @param v
     *        vector representation of force
     */
    public void applyForce (Vector v) {
        myAcceleration.sum(v);
    }

    /**
     * Convenience method. Finds distance between this mass and another.
     * 
     * @param other
     *        other mass
     * @return distance between this mass and other
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(), other.getY()));
    }

    /**
     * 
     * @return mass value
     */
    public double getMass () {
        return myMass;
    }
}
