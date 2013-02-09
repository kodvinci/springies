package simulation.forces.exponentforces;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Collection;

import simulation.elements.masses.Mass;
import util.Location;
import util.Vector;

/**
 * The center of mass force calculates the center of mass among a group of masses and then imposes a
 * force on each mass that brings it closer to the center of mass.
 * 
 * @author Erick Gonzalez
 */
public class CenterOfMassForce extends ExponentForce {
    private static final int TOGGLE_CENTER_OF_MASS_KEY = KeyEvent.VK_M;

    private Collection<Mass> myMasses;

    /**
     * Creates a center of mass force with a given set of masses, a magnitude, and exponent.
     * 
     * @param masses masses from which center of mass is determined
     * @param magnitude magnitude of center of mass force
     * @param exponent exponent value
     */
    public CenterOfMassForce(Collection<Mass> masses, double magnitude, double exponent) {
        super(magnitude, exponent);
        myMasses = masses;
    }

    private Location calculateCenterOfMass(Collection<Mass> masses) {
        double totalMass = 0;
        double momentX = 0;
        double momentY = 0;

        for (Mass m : masses) {
            totalMass += m.getMass();
            momentX += m.getX() * m.getMass();
            momentY += m.getY() * m.getMass();
        }

        double xBar = momentX / totalMass;
        double yBar = momentY / totalMass;

        return new Location(xBar, yBar);
    }

    @Override
    public Vector getVectorRepresentation(Mass m, Dimension bounds) {
        Location massLocation = new Location(m.getX(), m.getY());
        Location centerOfMassLocation = calculateCenterOfMass(myMasses);
        double direction = Vector.angleBetween(centerOfMassLocation, massLocation);
        double distance = Vector.distanceBetween(massLocation, centerOfMassLocation);

        return calculateExponentForce(m, bounds, direction, distance);
    }

    @Override
    public void tryToggle(int key) {
        if (key == TOGGLE_CENTER_OF_MASS_KEY) {
            toggle();
        }
    }
}
