package classes;

import org.vu.contest.ContestEvaluation;
import static classes.Population.random;

public class Individual {

    final static public int GENE_LENGTH = 10;
    private double[] genes = new double[GENE_LENGTH];
    private double[] sigmas = new double[GENE_LENGTH];
    private double fitness = Double.MIN_VALUE;
    static private double minValue = -5.0;
    static private double maxValue = 5.0;
    static private double minSigma = 0.000000001; 
    public double[] ni = new double[GENE_LENGTH];
    public double CROSS_RATE = 0.1*random.nextGaussian()+0.5;
   
    public Individual(boolean isMultimodal){
        for (int i = 0; i < GENE_LENGTH; i++) 
            ni[i] = random.nextGaussian();
    }

    //Generate random individual
    public void generateIndividual(ContestEvaluation eval) {
        for (int i = 0; i < GENE_LENGTH; i++) {
            double gene = random.nextDouble() * (maxValue - minValue) + minValue;
            genes[i] = gene;
            sigmas[i] = 1.0;
        }
        fitness = (Double) eval.evaluate(genes);
    }
    
	

    public double getGene(int position) {
            return genes[position];
    }

    public void setGene(int i, double d) {
        if (d > maxValue){
            genes[i] = (d-minValue)%(maxValue-minValue)+minValue;
        } else if (d < minValue) {
            genes[i] = (d-minValue)%(maxValue-minValue)+maxValue;
        } else {
            genes[i] = d;
        }
    }
    
    public double[] getGenes() {
        return genes;
    }
    
    public double getSigma(int i){
            return sigmas[i];
    }

    public void setSigma(int i, double d){
    	if (d<=minSigma) {
            sigmas[i] = minSigma;
        } else {
            sigmas[i] = d;
        }
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }
}