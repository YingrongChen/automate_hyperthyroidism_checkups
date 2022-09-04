
public class Run {

	public static void main(String[] args) {

		Patient p = new Patient(false, false, 20, true, false, true);
		if(p.getPregnant()) p.setTrimester(1);
		p.inputTFT(0.18, 1.3, 2.7);
		p.inputAntibody(true, 1.4);
		p.tracker(50, 75, true);
		p.tracker(52, 80, false);
		p.tracker(55, 88, false);

		boolean result;
		if(p.getOnMedication()) result = p.diagnosis();
		else result = p.remission();

		Patient p1 = new Patient(false, true, 17, false, true, false);
		if(p.getPregnant()) p.setTrimester(1);
		p.inputTFT(0.18, 1.3, 2.7);
		p.inputAntibody(true, 1.4);
		p.tracker(50, 75, true);
		p.tracker(52, 80, false);
		p.tracker(55, 88, false);

		boolean result1;
		if(p.getOnMedication()) result1 = p.diagnosis();
		else result1 = p.remission();
	}
}
