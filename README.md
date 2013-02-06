	Springies Project Group 8

	TEAM MEMBERS
1. Erick Gonzalez
2. Leonard Ng'eno

	DESCRIPTION
This project demonstrates how masses and springs interact with each other 
when various forces are applied on them. Such forces include gravity, center
of mass, viscosity and wall forces

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
