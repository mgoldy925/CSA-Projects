import java.util.Scanner;
import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PhysicsEquationTest {
	
	public static void main(String[] args) {
		Physics physics = new Physics();
		menu(physics);
	}
	
	public static void menu(Physics physics) {
		
		// Thank you to Stack Overflow for teaching me how to round decimals
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input 1 to solve for voltage, current, or resistance.\nInput 2 to solve for "
				+ "equivalent resistance.\nInput 3 to solve for power output of a circuit.\nInput 4 to solve for"
				+ " the charge, voltage, or energy stored on a capacitor.\nInput 5 to generate an Ohm's Law"
				+ " question with random values for you to solve.\nInput 0 to quit.");
		int input = scanner.nextInt();
		// Print new line to separate choice from following method
		System.out.println();
		
		switch(input) {
			case 0:
				return;
				
				
				
			case 1:
				// variables declared outside while loop so they can be used outside while loop
				int count = 0;
				double v = 0;
				double i = 0;
				double r = 0;
				
				while(count != 1) {
					// Get values for Ohm's Law
					System.out.println("Enter your voltage (in volts), current (in milliamps), and resistance"
							+ " (in Ohms).  Enter 0 for the one you are solving for.");
					
					System.out.print("Voltage:  ");
					v = scanner.nextDouble();
					
					System.out.print("Current:  ");
					i = scanner.nextDouble();
					
					System.out.print("Resistance:  ");
					r = scanner.nextDouble();
					
					// Check user only gave values for two of the variables in V = IR
					count = 0;
					if (v == -0) {
						count++;
					}
					if (i == 0) {
						count++;
					}
					if (r == 0) {
						count++;
					}
					
					if (count != 1) {
						System.out.println("Please enter 0 for only 1 of the variables.");
					}
				}
				
				// Calculate Ohm's Law
				double x = physics.OhmsLaw(v, i, r);
				
				// Format and return answer
				String variable = "";
				String unit = "";
				if (v == 0) {
					variable = "voltage";
					unit = " volt(s).";
				} else if (i == 0) {
					variable = "current";
					unit = " milliamp(s).";
				} else {
					variable = "resistance";
					unit = " Ohm(s).";
				}
				System.out.println("The " + variable + " is " + df.format(x) + unit);
				break;
				
				
				
			case 2:
				// Ask user if they are adding resistors in series or in parallel
				// Use check variable to keep while loop going until they correctly enter series or parallel
				String type = "";
				boolean check = true;
				
				// Added this line bc an extra enter was still in the input stream
				// according to Stack Overflow
				scanner.nextLine();
				
				while (check) {
					System.out.println("Are you adding resistors in series or in parallel?");
					type = scanner.nextLine();
					
					if (type.equalsIgnoreCase("parallel") || type.equalsIgnoreCase("series")) {
						check = false;
					} else {
						System.out.println("Please enter \"series\" or \"parallel\".");
					}
				}
				
				// Arraylist is ideal for this, I'm familiar with arrays and found array list are kinda like
				// arrays that are flexible, thank you w3schools
				ArrayList<Double> resistors = new ArrayList<Double>();
				double resistor = 1;
				System.out.println("Please enter the values for your resistors.  Enter 0 once you've "
						+ "entered in all of your resistors.");
				
				// For loop gets all resistor values from user
				for (int j = 0; resistor != 0; j++) {
					System.out.println("Resistor " + (j + 1) + ":  ");
					resistor = scanner.nextDouble();
					
					if (resistor != 0) {
						resistors.add(resistor);
					}
				}
				
				// Run method to get equivalent
				double eq = 0;
				if (type.equalsIgnoreCase("series")) {
					eq = physics.EqResistorSeries(resistors);
				} else {
					eq = physics.EqResistorParallel(resistors);
				}
				
				System.out.println("Your equivalent resistance is " + df.format(eq) + " Ohms.");
				break;
				
				
				
			case 3:
				// variables declared outside while loop so they can be used outside while loop
				int counter = 0;
				double vol = 0;
				double cur = 0;
				double res = 0;
				
				while(counter != 1) {
					// Get values for power calculation
					System.out.println("Enter your voltage (in volts), current (in amps), and resistance (in "
							+ "Ohms).  Enter 0 for the one you are solving for.");
					
					System.out.print("Voltage:  ");
					vol = scanner.nextDouble();
					
					System.out.print("Current:  ");
					cur = scanner.nextDouble();
					
					System.out.print("Resistance:  ");
					res = scanner.nextDouble();
					
					// Check user only gave values for two of the variables
					counter = 0;
					if (vol == -0) {
						counter++;
					}
					if (cur == 0) {
						counter++;
					}
					if (res == 0) {
						counter++;
					}
					
					if (counter != 1) {
						System.out.println("Please enter 0 for only 1 of the variables.");
					}
					
				}
				
				// Run method to calculate power
				double power = physics.power(vol, cur, res);
				System.out.println("The power output of the circuit is " + df.format(power) + " joules.");
				
				break;
				
				
				
			case 4:
				// Get a capacitance value greater than 0 from user
				double cap = 0;
				while (cap <= 0) {
					System.out.println("What is the capacitance of your capacitor?");
					cap = scanner.nextDouble();
					
					if (cap <= 0) {
						System.out.println("Please enter a positive number greater than 0");
					}
				}
				
				// Set capacitor value
				physics.setCapacitance(cap);
				
				// Ask which value they want to solve for, charge or voltage
				// Declare variable now for scope reasons
				// Checker variable keeps while loop running if they don't pick one option
				String which = "";
				boolean checker = true;
				double q = 0;
				double volt = 0;
				scanner.nextLine();
				
				while (checker) {
					System.out.println("Do you want to input charge or voltage?");
					which = scanner.nextLine();
					
					if (which.equalsIgnoreCase("charge") || which.equalsIgnoreCase("voltage")) {
						checker = false;
						
						// Get charge/voltage, ensure they are greater than 0
						boolean checking = true;
						while (checking) {
							System.out.print("Please enter your " + which.toLowerCase() + ":  ");
							double placeholder = scanner.nextDouble();
							
							// Properly store charge/voltage values bc other value must be 0
							if (placeholder < 0) {
								System.out.println("Please enter a value greater than 0");
							} else {
								checking = false;
								
								if (which.equalsIgnoreCase("charge")) {
									q = placeholder;
									volt = 0;
								} else {
									volt = placeholder;
									q = 0;
								}
							}
						}
						
					} else {
						System.out.println("Please enter \"charge\" or \"voltage\".");
					}
				}
				
				// Aliasing right here, circuit now has the same address as physics
				Physics circuit = physics;
				
				double y = circuit.CapacitorQV(q, volt);
				double energy = circuit.CapacitorEnergy(q, volt);
				
				// I want to state the proper units for the variable that was solved for in the terminal
				String units = "";
				if (q == 0) {
					units = " coulombs.";
				} else {
					units = " volts.";
				}
				
				// I plan to print the opposite of which when giving the answer, so I flip it here
				if (which.equalsIgnoreCase("charge")) {
					which = "voltage";
				} else {
					which = "charge";
				}
				
				// I'm gonna fix the grammar if the unit is singular, I'm being extra as with this entire project
				String z = df.format(y);
				if (z.equals("1")) {
					units = units.substring(0, (units.length() - 2)) + ".";
				}
				
				System.out.println("The " + which + " is " + z + units);
				System.out.println("The capacitor has " + df.format(energy) + " joule(s) stored on it.");
				
				break;
				
				
				
			case 5:
				// Asking if they want to input voltage, current, or resistance and making sure they do
				String choice = "";
				check = true;
				scanner.nextLine();
				
				while (check) {
					System.out.println("Would you like to solve for voltage, current, or resistance?");
					choice = scanner.nextLine();
					
					if (choice.equalsIgnoreCase("voltage") || choice.equalsIgnoreCase("current") || 
							choice.equalsIgnoreCase("resistance")) {
						check = false;
					} else {
						System.out.println("Please enter \"voltage\", \"current\", or \"resistance\".");
					}
				}
				
				// Generate random values and make arrays for printing out the word problem
				double[] values = physics.GenerateOhmsLawProblem();
				
				// Change all the values to not be super long decimals
				for (int l = 0; l < 3; l++) {
					
					// In order to format with df, I had to convert the strings it produced into to doubles
					// Thank you to beginnersbook.com for how to do that
					String value  = df.format(values[l]);
					values[l] = Double.parseDouble(value);
				}
				
				String[] words = {"voltage", "current", "resistance"};
				String[] theUnits = {" volts.  ", " milliamps.  ", " ohms.  "};
				int save = -1;
				
				// This for loop will print out the problem, printing what each value is
				for (int k = 0; k < 3; k++) {
					
					// This if statement identifies which value is being solved for so that the program
					// can ask about that value outside of the for loop
					if (choice.equalsIgnoreCase(words[k])) {
						save = k;
						continue;
					}
					
					// This is just making the unit singular if the value is 1, very extra
					if (values[k] == 1) {
						theUnits[k] = theUnits[k].substring(0, (theUnits[k].length() - 4)) + ".  ";
					}
					
					System.out.print("The " + words[k] + " is " + values[k] + theUnits[k]);
				}
				
				// Have the user type in the answer
				System.out.println("What is the " + words[save] + "?  Round to two decimal places.");
				scanner.nextDouble();
				System.out.println("The answer is about " + values[save] + theUnits[save]);
				
				break;
				
				
				
			default:
				System.out.println("Please input a valid number.");
		}
		// Print out new line to separate menu
		System.out.println();
		
		// I wanna try recursion, I've used it before in programs
		menu(physics);
	}
}
