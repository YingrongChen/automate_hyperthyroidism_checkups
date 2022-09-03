
public class Patient {
	
	//non-clinic features;
	private static double weight;
	private static double heartbeat;
	private static boolean medCompliance;
	
	//clinic features;
	private static double BloodTest;
	
	
	public Patient() {
		this.weight = Integer.MIN_VALUE;
		this.heartbeat = Integer.MIN_VALUE;
		this.medCompliance = false;
		this.BloodTest = Integer.MIN_VALUE;
	}

	public Patient(double weight, double heartbeat, boolean medComliance, double BloodTest) {
		this.weight = weight;
		this.heartbeat = heartbeat;
		this.medCompliance = medCompliance;
		this.BloodTest = BloodTest;
	}
	
}
