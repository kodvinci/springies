package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import simulation.elements.masses.FixedMass;
import simulation.elements.masses.Mass;
import simulation.elements.springs.Muscle;
import simulation.elements.springs.Spring;
import simulation.forces.GravitationalForce;
import simulation.forces.ViscousForce;
import simulation.forces.exponentforces.CenterOfMassForce;
import simulation.forces.exponentforces.walls.BottomWallRepulsionForce;
import simulation.forces.exponentforces.walls.LeftWallRepulsionForce;
import simulation.forces.exponentforces.walls.RightWallRepulsionForce;
import simulation.forces.exponentforces.walls.TopWallRepulsionForce;
import simulation.forces.exponentforces.walls.WallRepulsionForce;

/**
 * A factory class that parses an input file containing different elements or forces and creates
 * in-memory representations of them.
 * 
 * @author Robert C. Duvall
 * @author Erick Gonzalez
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTER_MASS_KEYWORD = "centermass";
    private static final String WALL_REPULSION_KEYWORD = "wall";
    private static final String MUSCLE_KEYWORD = "muscle";

    // wallIDs
    private static final int TOP_WALL = 1;
    private static final int RIGHT_WALL = 2;
    private static final int BOTTOM_WALL = 3;
    private static final int LEFT_WALL = 4;

    // mass IDs
    private Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

    /**
     * Parses elements from a formatted file and stores them.
     * 
     * @param model
     *            model where elements will be stored
     * @param modelFile
     *            formatted file from which elements are parsed
     */
    public void loadElements(Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        model.add(createMass(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        model.add(createSpring(line));
                    }
                    else if (MUSCLE_KEYWORD.equals(type)) {
                        model.add(createMuscle(line));
                    }
                }
            }
            input.close();

        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    /**
     * Parses forces from a formatted file and stores them.
     * 
     * @param model
     *            the model where elements will be stored
     * @param modelFile
     *            formatted file from which forces are parsed
     */
    public void loadForces(Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                        model.add(createGravitationalForce(line));
                    }
                    else if (VISCOSITY_KEYWORD.equals(type)) {
                        model.add(createViscosityForce(line));
                    }
                    else if (CENTER_MASS_KEYWORD.equals(type)) {
                        model.add(createCenterOfMassForce(line));
                    }
                    else if (WALL_REPULSION_KEYWORD.equals(type)) {
                        model.add(createWallRepulsionForce(line));
                    }
                }
            }
            input.close();

        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    private WallRepulsionForce createWallRepulsionForce(Scanner line) {
        int wallId = line.nextInt();
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();

        switch (wallId) {
            case TOP_WALL:
                return new TopWallRepulsionForce(magnitude, exponent);
            case RIGHT_WALL:
                return new RightWallRepulsionForce(magnitude, exponent);
            case BOTTOM_WALL:
                return new BottomWallRepulsionForce(magnitude, exponent);
            case LEFT_WALL:
                return new LeftWallRepulsionForce(magnitude, exponent);
            default:
                return null;
        }
    }

    private CenterOfMassForce createCenterOfMassForce(Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        return new CenterOfMassForce(myMasses.values(), magnitude, exponent);
    }

    private GravitationalForce createGravitationalForce(Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        return new GravitationalForce(angle, magnitude);
    }

    private ViscousForce createViscosityForce(Scanner line) {
        double scaleValue = line.nextDouble();
        return new ViscousForce(scaleValue);
    }

    private Mass createMass(Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        Mass result;
        if (mass >= 0) {
            result = new Mass(x, y, mass);
        }
        else {
            result = new FixedMass(x, y, mass);
        }
        myMasses.put(id, result);
        return result;
    }

    private Spring createSpring(Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }

    private Spring createMuscle(Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        double amp = line.nextDouble();
        return new Muscle(m1, m2, restLength, ks, amp);
    }
}
