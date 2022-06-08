import java.util.ArrayList;

public class Physics {
	
	// private instance variables
	private double myCapacitance;
	
	// constructors
	public Physics() {
		myCapacitance = 0;
	}
	
	public Physics(double c) {
		myCapacitance = c;
	}
	
	// accessor methods
	public double getCapacitance() {
		return myCapacitance;
	}
	
	// modifier methods
	public void setCapacitance(double c) {
		myCapacitance = c;
	}
	
	// Ohm's Law method, V = IR, v is voltage, i is current, r is resistance
	// Precondition: only one parameter is 0
	public double OhmsLaw(double v, double i, double r) {
		
		double ans = 0;
		
		// All the 1000's are converting between amps and milliamps
		if (v == 0) {
			ans = i * r / 1000;
		} else if (i == 0) {
			ans = v / r * 1000;
		} else {
			ans = v / i * 1000;
		}
		
		return ans;
	}
	
	// Series resistor method, Req = R1 + R2 + ...
	public double EqResistorSeries(ArrayList<Double> r) {
		
		double eq = 0;
		
		// I found this type of for loop online, it reminds me of Python and JavaScript
		for (double i : r) {
			eq += i;
		}
		
		return eq;
	}
	
	// Parallel resistor method, 1/Req = 1/R1 + 1/R2 + ...
	public double EqResistorParallel(ArrayList<Double> r) {
		
		double eq = 0;
		
		for (double i : r) {
			eq += 1 / i;
		}
		
		return 1 / eq;
	}
	
	// Determine power output of circuit, P = IV
	// Ohm's Law can be used to get other equations: P = I^2*R, P = V^2 / R
	// Precondition: Only one parameter is 0
	public double power(double v, double i, double r) {
		
		double p = 0;
		
		if (v == 0) {
			p = Math.pow(i, 2) * r;
		} else if (i == 0) {
			p = Math.pow(v, 2) / r;
		} else {
			p = v * i;
		}
		
		return p;
	}
	
	// Method to get charge/voltage on capacitor, C = Q / V
	// Precondition: One variable is 0
	// Precondition: Capacitance has been stored
	public double CapacitorQV(double q, double v) {
		double c = myCapacitance;
		double answer = 0;
		
		if (q == 0) {
			answer = c * v;
		} else {
			answer = q / c;
		}
		
		return answer;
	}
		
	// Method to get energy on capacitor, E = 1/2 * Q * V
	// Other equations derived with capacitor equation, E = 1/2 * C * V^2, E = 1/2 * Q^2 / C
	// Precondition: One variable is 0
	// Precondition: Capacitance has been stored
	public double CapacitorEnergy(double q, double v) {
		double c = myCapacitance;
		double energy = 0;
		
		if (q == 0) {
			energy = c * Math.pow(v, 2) / 2;
		} else {
			energy = Math.pow(q, 2) / c / 2;
		}
		
		return energy;
	}
		
		
	// Method to generate random circuit problem
	public double[] GenerateOhmsLawProblem() {
			
		double v = 5 * Math.random() + 2;
		double r = 20 * Math.random() + 11;
		double i = v / r * 1000;
			
		// Store in array so I can work with all 3 values in program
		double[] list = {v, i, r};
			
		return list;
	}
		
		
}
