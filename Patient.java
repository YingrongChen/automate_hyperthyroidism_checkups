import java.util.ArrayList;

public class Patient {
	//basic info
	private boolean gender; //false - female, true - male
	private boolean pregnant; //false - nonpregnant
	private int trimester; //true if pregnant
	private int age;
	private boolean smoke; //true if smoke
	private boolean symptoms;
	//Rapid heartbeat, Heart palpitations, Unexplained weight loss, Increased appetite, Restlessness, Decreased attention span, Shortness of breath, Muscle weakness

	//non-clinic features - weekly tracker
	private ArrayList<Double> weight;
	private ArrayList<Integer> heartRate;
	private ArrayList<Boolean> medCompliance;
//skaldjla:wq

	//clinic features;
	//thyroid function tests
	private double TSH;
	private double T4;
	private double T3;
	//antibody tests
	private boolean TRAb; //positive or negative
	private double TSI;

	//result
	private double risk = 1;

	private static final double THRESHOLD = 1.2;

	public Patient() {
		this.gender = false; //female
		this.pregnant = false; //non-pregnant
		this.age = Integer.MIN_VALUE;
		weight = new ArrayList<>();
		heartRate = new ArrayList<>();
		medCompliance = new ArrayList<>();
	}

	public Patient(boolean gender, boolean pregnant, int age) {
		this.gender = gender; //female
		this.pregnant = pregnant; //non-pregnant
		this.age = age;
		weight = new ArrayList<>();
		heartRate = new ArrayList<>();
		medCompliance = new ArrayList<>();
	}

	public Patient(boolean gender, boolean pregnant, int trimester, int age) {
		this(gender, pregnant, age);
		if (pregnant) this.trimester = trimester;
	}

	public void tracker(double weight, int heartRate, boolean medCompliance) {
		this.weight.add(weight);
		this.heartRate.add(heartRate);
		this.medCompliance.add(medCompliance);
	}
	
	public void inputTFT(double TSH, double T4, double T3){
		this.TSH = TSH;
		this.T4 = T4;
		this.T3 = T3;
	}
	
	public void inputAntibody(boolean TRAb, double TSI){
		this.TRAb = TRAb;
		this.TSI = TSI;
	}

	public boolean diagnosis() {
		if (pregnant){
			switch (trimester){
				case 1: 
					if (TSH < 0.1 || TSH > 4.7 ) risk *= 0.01; //TSH Reference Range: 0.1–2.5 mIU/L
					break;
				case 2:
					if (TSH < 0.2 || TSH > 3.0 ) risk *= 0.01; //TSH Reference Range: 0.2–3.0 mIU/L
					break;
				case 3:
					if (TSH < 0.3 || TSH > 3.0 ) risk *= 0.01; //TSH Reference Range: 0.3–3.0 mIU/L
					break;
			}
		}
		if (T3 < 3.0 || T3 > 4.7 ) risk *= 0.01; //T3 Reference Range: 3.0-4.7 pg/mL
		if (T4 < 0.8 || T4 > 1.4 ) risk *= 0.01; //T4 Reference Range: 0.8-1.4 ng/dL
		
		if (TSI > 1.3) risk *= 0.01;
		if (TRAb) risk *= 0.02;

		if (risk > THRESHOLD) return true;
		return false;
	}
	
	public static boolean remission(Patient p) {
		return true;
	}
}
