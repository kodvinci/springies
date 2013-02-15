	Springies Project Group 8

	TEAM MEMBERS
1. Erick Gonzalez
2. Leonard Ng'eno

					DESCRIPTION

This project demonstrates how masses and springs interact with each other 
when various forces are applied on them. Such forces include gravity, center
of mass, viscosity and wall forces. 

			Overview
The core of this project's design is the inheritance hierarchy. The various force
classes inherit from the Force class while the mass and spring elements inherit from 
the Sprite class. The FixedMass class inherits from the Mass class while the Muscle class
inherits from the Spring class. We did not group the various drawable elements under one 
super class since we felt that it was easier to draw the elements individually. The drawing
is handled by the Model class which also takes care of regularly updating the elements. 
User input, such as key presses and mouse movements, are handled by the UserInputListener class.
The Model class regularly checks with the UserInputListener class every time the update method
is called.

To load various assemblies, we simply create the respective elements and store them in the Lists
where trhe elements for the current assembly(ies) are stored. We did not feel the need for an 
Assembly class since the project does not require for any differenetion between the existing
assemblies, e.g, there is no requirement to clear a particular assembly or to have one particular
assembly being impacted by a particular set of forces only.


	Key codes
1. Press "n" to load new assemblies.
2. Press "c" to clear all assemblies.
3. Press "g", "v", "m" to turn on or off the force of gravity, viscosity or center of mass respectively.
4. Press "1", "2", "3", "4" to turn on or off the top, right, bottom, and left wall forces respectively.

The project is organized into various packages that perform particular functions.

	1. Simulation Package
This package handles the reading of the input files containing the data for the 
simulation. It then parses the input, stores them in various data structures
and then regularly paints them on the display screen. 

		a. Factory Class
Reads the input files and stores the data based on the key word that appears as 
the first item in every line. 

The input file containing masses, springs, and muscles is read first. The data that 
appears in each line is used to instantiate a class corresponding to the first word 
in that line. The instances are then added to their respective Containers/Lists
in the Model class. 

The file containing the environment forces is then read next and its data 
manipulated in the same way that the masses and springs were dealt with.

		b. Model Class	
This class draws and regularly updates the elements of the game on the given Canvas.
It also applies the environmental forces on the masses. The masses and springs are 
held in Lists while the forces are stored in Collections. The major methods are:
1. paint - draws the springs and masses
2. update - updates the simulation after a certain time period has elapsed
3. applyEnvironmentalForces - applies the given force to the given mass

	2. Simulation.elements.masses Package
It has two classes, Mass and FixedMass class. The Mass class creates a mass object 
that a force can act on, i.e. force can be applied to the mass and as a result 
change its acceleration.

The FixedMass class is a subclass of the Mass class. The major difference between it 
and the Mass class is that it does not respond to any force that is applied to it.

	3. Simulation.elements.springs
This package contains classes that represent objects that connect two mass objects.

		a. Spring
The Spring class is a subclass of the Sprite class. It overrides its update method,
and implements it by creating a force based on the distance and angles between
the two masses it connects. It then applies that force on the masses.

		b. Muscle
This class extends the Spring class. It overrides its update method. The major 
difference between it and the Spring class is that the length of the muscle
varies based on a number of factors such as its amplitude and oscillation. 

	4. Simulation.forces
This package contains Force classes.

		a. Force
An abstract class that represents a force. It has one abstract method, 
getVectorRepresentation, that gives the vector representation of this force. The 
force can be applied to a mass within some given bounds.

		b. BoundsForce 
A subclass of Force. It is the force that is responsible for bouncing masses off 
a wall when they come into contact with. Its implementation of the 
getVectorRepresentation method entails creating an impulsive force that pushes 
the mass in the opposite direction.

		c. GravitationalForce
A subclass of the Force class that represents the force of gravity. Its 
getVectorRepresentation method simply creates a force that is proportional 
to the mass of the Mass object, i.e. a scaled value of its magnitude.

		d. ViscousForce
A subclass of Force that represents a resistive force that acts in the opposite 
direction of a mass's velocity. Its getVectorRepresentation method simply negates
the velocity of the given mass and then scales it by a given value.

	5. Simulation.forces.exponentforces
This package contains forces who magnitudes vary inversely with the distance

		a. ExponentForce class
Inherits from Force class. If the exponent of the force is 2.0, the magnitude
of the force varies inversely with distance. 

		b. CenterOfMassForce
Extends ExponentForce class. The center of mass force calculates the center 
of mass among a group of masses and then imposes a force on each mass that 
brings it closer to the center of mass.

	6. Simulation.forces.exponentforces.walls
This package contains forces exerted by the walls on the masses and springs

		WallRepulsionForce class
Extends ExponentForce class. Abstract class representing the repulsion forces
of the walls. Forces the mass to change direction.

The following classes implement the WallRepulsionForce class:
a. TopWallRepulsionForce - repulsion force of top wall. Acts downwards
b. BottomWallRepulsionForce - repulsion force of the bottom wall. Acts upwards
c. LeftWallReplsuionForce - repulsion force of the left wall. Acts rightwards
d. RightWallRepulsionForce - repulsion force of the right wall. Acts leftwards
