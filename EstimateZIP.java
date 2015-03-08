import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class EstimateZIP {
    private double p;
    private double t;
    private int[] x;
    private double[] v;
    private static final double TOLERANCE = 0.0001;
    /**
     * constructs a Zero Inflated Poisson Model estimator
     * @param x - the sample (non negative numbers)
     */
    public EstimateZIP(int[] x){
        for (int i = 0; i < x.length; i++)
            assert x[i]>=0 : "Sample at index "+i+" is negative.";
        this.x = x;
        v = new double[x.length];
        estimate();
    }

    /**
     * @return the estimate of the probability that v = 1
     */
    public double getP(){
        return p;
    }

    /**
     * @return the parameter theta of the poisson distribution
     */
    public double getT(){
        return t;
    }

    /**
     * @return an array of probabilities that v = 1 for each value in the sample
     */
    public double[] getV(){
        return v;
    }

    // estimates the parameters
    private void estimate(){
        init();
        boolean stop=false;
        while (!stop){
            expectation();
            stop = maximization();
        }
    }

    // initializes the parameters usingt MOM
    private void init(){
        double sumx = 0;
        double sumsqx = 0;
        for (int i = 0; i < x.length; i++){
            sumx += x[i];
            sumsqx += x[i]*x[i];
        }
        t = sumsqx / sumx - 1;
        p = sumx * sumx / (sumsqx - sumx);
    }

    // estimates v
    private void expectation(){
        for (int i = 0; i < v.length; i++){
            double pdf =  poisspdf(x[i], t);
            if (x[i]==0){
                v[i] = pdf * p / (pdf * p + (1-p));
            }else{
                v[i] = 1;
            }
        }
    }

    // estimates p and t using v
    private boolean maximization(){
        double newp = 0;
        double newt = 0;
        for (int i = 0; i < v.length; i++){
            newp += v[i];
            newt += v[i] * x[i];
        }
        newt /= newp;
        newp /= v.length;
        double diffp = Math.abs(newp - p);
        double difft = Math.abs(newt - t);
        p = newp;
        t = newt;
        if ( diffp < TOLERANCE && difft < TOLERANCE ){
            return true;
        }else{
            return false;
        }
    }

    private double poisspdf(int x, double theta){
        return Math.exp(-theta) * Math.pow(theta, x) / factorial(x);
    }

    private int factorial(int x){
        int f = 1;
        for (int i = 1; i <= x; i++){
            f *= i;
        }
        return f;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner reader = new Scanner(new FileReader(args[0]));
        int size = Integer.parseInt(args[1]);
        int[] x = new int[size];
        for (int i = 0; i < size; i++){
            x[i] = reader.nextInt();
        }
        reader.close();

        EstimateZIP est = new EstimateZIP(x);
        System.out.println("p = " + est.getP() + "\n" + "t = " + est.getT() + "\n");



    }


}
