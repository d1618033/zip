public class GenerateZIP {
	private double p;
	private double t;
	/**
	 * @param p - probability of being drawn from a poisson model
	 * 			  as oppose to being drawn from 0
	 * @param t - parameter of poisson model
	 */
	public GenerateZIP(double p, double t){
		this.p = p;
		this.t = t;
	}
	/**
	 * @return an instance of a ZIP rv
	 */
	public int generate(){
		double u = Math.random();
		if (u < p){
			return randPoisson();
		}else{
			return 0;
		}
	}
	// return an instance of a poisson rv
	private int randPoisson(){
		double u = Math.random();
		double current = Math.exp(-t);
		double sum = current;
		int index = 0;
		while (u > sum){
			index++;
			current *= t / index;
			sum += current;
		}
		return index;
	}
	public static void main(String[] args){
		double p = Double.parseDouble(args[0]);
		double t = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		
		GenerateZIP g = new GenerateZIP(p, t);
		for (int i = 0; i < n; i++){
			System.out.println(g.generate());
		}
	}
	
}
