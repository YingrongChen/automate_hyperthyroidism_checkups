import java.util.ArrayList;

public class Patient {

/////////////////////////////////////////
// Fields
/////////////////////////////////////////

	//basic info
	private boolean gender; //false - female, true - male
	private boolean pregnant; //false - nonpregnant
	private int age;
	private boolean onMedication;
	private boolean smoke; //true if smoke
	private boolean symptoms;
	//Rapid heartbeat, Heart palpitations, Unexplained weight loss, Increased appetite, Restlessness, Decreased attention span, Shortness of breath, Muscle weakness

	//if pregnant
	private int trimester;

	//non-clinic features - weekly tracker
	private ArrayList<Double> weight; // unit: kg
	private ArrayList<Double> heartRate; // unit: beats/minute
	private ArrayList<Boolean> medCompliance;
//skaldjla:wq

	//clinic features;
	//thyroid function tests
	private double TSH; // Thyroid-stimulating hormone, unit: mIU/L
	private double T4; // Free thyroxine , unit: ng/dL
	private double T3; // Free triiodothyronine, unit: pg/mL
	//antibody tests
	private boolean TRAb; //TSH receptor antibody, positive or negative
	private double TSI; // Thyroid-stimulating immunoglobulin, percentage

	private static final double THRESHOLD = 1.2;


/////////////////////////////////////////
// Constructors
/////////////////////////////////////////

	public Patient() {
		weight = new ArrayList<>();
		heartRate = new ArrayList<>();
		medCompliance = new ArrayList<>();
	}

	public Patient(boolean gender, boolean pregnant, int age, boolean onMedication, boolean smoke, boolean symptoms) {
		this();
		this.gender = gender; //female
		this.pregnant = pregnant; //non-pregnant
		this.age = age;
		this.onMedication = onMedication;
		this.smoke = smoke;
		this.symptoms = symptoms;
	}

/////////////////////////////////////////
// Setter
/////////////////////////////////////////
	public void setTrimester(int trimester) {
		this.trimester = trimester;
	}

	public void tracker(double weight, double heartRate) {
		this.weight.add(weight);
		this.heartRate.add(heartRate);
	}

	public void tracker(double weight, double heartRate, boolean medCompliance) {
		tracker(weight, heartRate);
		if (onMedication) this.medCompliance.add(medCompliance);
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

/////////////////////////////////////////
// Getter
/////////////////////////////////////////
	public boolean getOnMedication(){
		return onMedication;
	}

	public boolean getPregnant(){
		return pregnant;
	}


/////////////////////////////////////////
// Functions
/////////////////////////////////////////
	public boolean diagnosis() {
		double risk = riskCalculate();
		if (onMedication && nonCompile(medCompliance, 0.5))  risk *= 1.002; //the patient is not eating medication as he/she should
		if (risk > THRESHOLD) return true;
		return false;
	}
	
	public boolean remission() {
		double risk = riskCalculate();
		if(risk > THRESHOLD*0.8) return true; //lower threshold for patient who already got 
		return false;
	}

	private double riskCalculate(){
		//clinical 
		double risk = 0;
		if (pregnant){
			switch (trimester){
				case 1: 
					if (TSH < 0.1 || TSH > 2.5 ) risk *= 1.01; //TSH Reference Range: 0.1–2.5 mIU/L
					break;
				case 2:
					if (TSH < 0.2 || TSH > 3.0 ) risk *= 1.01; //TSH Reference Range: 0.2–3.0 mIU/L
					break;
				case 3:
					if (TSH < 0.3 || TSH > 3.0 ) risk *= 1.01; //TSH Reference Range: 0.3–3.0 mIU/L
					break;
			}
		} else {
			if (TSH < 0.5 || TSH > 4.7 ) risk *= 1.01;
		}
		if (T3 < 3.0 || T3 > 4.7 ) risk *= 1.01; //T3 Reference Range: 3.0-4.7 pg/mL
		if (T4 < 0.8 || T4 > 1.4 ) risk *= 1.01; //T4 Reference Range: 0.8-1.4 ng/dL

		if (TSI > 1.3) risk *= 1.01;
		if (TRAb) risk *= 1.02;

		//non-clinical
		if (symptoms) risk *= 1.01;
		if (!gender) risk *= 1.002;
		if (smoke) risk *= 1.002;
		if (age >= 20 && age <= 50) risk *= 1.002;
		if (!increase(weight, 2)) risk *= 1.002; //weight is decreasing
		if (increase(heartRate, 5)) risk *= 1.002; //heart rate is increasing

		return risk;
	}

/////////////////////////////////////////
// Helper
/////////////////////////////////////////
	// check trend in the array
	private static boolean increase(ArrayList<Double> array, double acceptChange){
		int sum = 0;
		for (int i = 1; i < array.size(); i++){
			int importance = 1 + 2^(-5 * i);
			if (Math.abs(array.get(i) - array.get(i-1)) > acceptChange*importance){
				sum += array.get(i) - array.get(i-1);
			}
		}
		if (sum > 0) return true;
		return false;
	}

	private static boolean nonCompile(ArrayList<Boolean> array, double acceptPercent){
		int nonCompliance = 0;
		for (boolean b: array){
			if (!b) nonCompliance++;
		}
		if (nonCompliance/array.size() > acceptPercent) return true;
		return false;
	}
}
