
public class Run {

	public static void main(String[] args) {
		Patient p = new Patient();
		Functions f = new Functions();
		
		if(f.diagnose(p)) {
			if (f.treatment(p)) {
				System.out.println(f.postTreatment(p));
			}
			else {
				System.out.println(f.treatment(p));
			}
		}
		else {
			System.out.println(f.diagnose(p));
		}
	}
	
	
	
}
