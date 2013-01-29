package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import simulation.elements.FixedMass;
import simulation.elements.Mass;
import simulation.elements.Muscle;
import simulation.elements.Spring;
import simulation.forces.GravitationalForce;
import simulation.forces.ViscousForce;
import simulation.forces.exponentforces.CenterOfMassForce;
import simulation.forces.exponentforces.walls.BottomWallRepulsionForce;
import simulation.forces.exponentforces.walls.LeftWallRepulsionForce;
import simulation.forces.exponentforces.walls.RightWallRepulsionForce;
import simulation.forces.exponentforces.walls.TopWallRepulsionForce;
import simulation.forces.exponentforces.walls.WallRepulsionForce;

/**
 * XXX
 * 
 * @author Robert C. Duvall
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
	
	// mass IDs
	Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

	/**
	 * XXX.
	 */
	public void loadModel(Model model, File modelFile) {
		try {
			Scanner input = new Scanner(modelFile);
			while (input.hasNext()) {
				Scanner line = new Scanner(input.nextLine());
				if (line.hasNext()) {
					String type = line.next();
					if (MASS_KEYWORD.equals(type)) {
						model.add(massCommand(line));
					} else if (SPRING_KEYWORD.equals(type)) {
						model.add(springCommand(line));
					} else if (MUSCLE_KEYWORD.equals(type)) {
						model.add(muscleCommand(line));
					}
					
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			// should not happen because File came from user selection
			e.printStackTrace();
		}
	}

	public void loadEnvironment(Model model, File modelFile) {
		try {
			Scanner input = new Scanner(modelFile);
			while (input.hasNext()) {
				Scanner line = new Scanner(input.nextLine());
				if (line.hasNext()) {
					String type = line.next();
					if (GRAVITY_KEYWORD.equals(type)) {
						model.add(gravityCommand(line));
					} else if (VISCOSITY_KEYWORD.equals(type)) {
						model.add(viscosityCommand(line));
					} else if (CENTER_MASS_KEYWORD.equals(type)) {
						model.add(centerMassCommand(line));
					} else if (WALL_REPULSION_KEYWORD.equals(type)) {
						model.add(wallRepulsionCommand(line));
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			// should not happen because File came from user selection
			e.printStackTrace();
		}
	}

	private WallRepulsionForce wallRepulsionCommand(Scanner line) {
		int wallId = line.nextInt();
		double magnitude = line.nextDouble();
		double exponent = line.nextDouble();

		switch (wallId) {
			case 1:
				return new TopWallRepulsionForce(magnitude, exponent);
			case 2:
				return new RightWallRepulsionForce(magnitude, exponent);
			case 3:
				return new BottomWallRepulsionForce(magnitude, exponent);
			case 4:
				return new LeftWallRepulsionForce(magnitude, exponent);
			default:
				return null;
		}
	}

	private CenterOfMassForce centerMassCommand(Scanner line) {
		double magnitude = line.nextDouble();
		double exponent = line.nextDouble();
		return new CenterOfMassForce(myMasses.values(), magnitude, exponent);
	}

	// create gravity from formatted data
	private GravitationalForce gravityCommand(Scanner line) {
		double angle = line.nextDouble();
		double magnitude = line.nextDouble();
		return new GravitationalForce(angle, magnitude);
	}

	private ViscousForce viscosityCommand(Scanner line) {
		double scaleValue = line.nextDouble();
		return new ViscousForce(scaleValue);
	}

	// create mass from formatted data
	private Mass massCommand(Scanner line) {
		int id = line.nextInt();
		double x = line.nextDouble();
		double y = line.nextDouble();
		double mass = line.nextDouble();
		Mass result;
		if (mass >= 0) {
			result = new Mass(x, y, mass);
		} else {
			result = new FixedMass(x, y, mass); 
		}		
		myMasses.put(id, result);
		return result;
	}

	// create spring from formatted data
	private Spring springCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		return new Spring(m1, m2, restLength, ks);
	}
	
	//create muscle from formatted data
	private Spring muscleCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		double amp = line.nextDouble();
		return new Muscle(m1, m2, restLength, ks, amp);
	}
	
}
