package simulation.elements.springs;

import java.awt.Dimension;

import simulation.elements.masses.Mass;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;

/**
 * Creates a spring object.
 * 
 * @author Robert C. Duvall
 */
public class Spring extends Sprite {
    // reasonable default values
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("spring.gif");
    private static final int IMAGE_HEIGHT = 20;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;

    /**
     * @param start
     *            starting mass
     * @param end
     *            ending mass
     * @param length
     *            distance between masses
     * @param kVal
     *            spring constant
     */
    public Spring(Mass start, Mass end, double length, double kVal) {
        super(DEFAULT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    @Override
    /**
     * 
     */
    public void update(double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();
        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy), myK
                * (myLength - Vector.distanceBetween(dx, dy)));
        myStart.applyForce(force);
        force.negate();
        myEnd.applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

    /**
     * 
     * @param start
     *            start mass
     * @param end
     *            end mass
     * @return
     */
    public static Location getCenter(Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * 
     * @param start
     *            start mass
     * @param end
     *            end mass
     * @return
     */
    public static Dimension getSize(Mass start, Mass end) {
        return new Dimension((int) start.distance(end), IMAGE_HEIGHT);
    }

    protected void setMyLength(double length) {
        myLength = length;
    }
}
