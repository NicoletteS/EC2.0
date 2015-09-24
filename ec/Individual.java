package ec;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;

public class Individual {
	
	static int GEN_LENGTH = 10;
	static private double MIN_VALUE = -5;
	static private double MAX_VALUE = 5;
	
	private ContestEvaluation evaluation_;
	private double[] genes;
	private double fitness;
	
	public Individual() {
		
	}
	//Generate random individual
	public void generateIndividual(ContestEvaluation eval) {
		evaluation_ = eval;
		genes = new double[GEN_LENGTH];
		Random rand = new Random();
		for (int i=0; i<GEN_LENGTH;i++) {
			double gene = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
			genes[i] = gene;
		}
//		Arrays.fill(genes, getRandomGen());
	}
	
	//Get genotype
	public void getGenotype(ContestEvaluation evaluation, double[] genotype) {
		evaluation_ = evaluation;
		this.genes = genotype;
	}
	
	//Get fitness level
	public void evaluate() {
		fitness = ((Double) evaluation_.evaluate(genes)).doubleValue();
	}
	
/*	private double getRandomGen() {
		Random rand = new Random();
		return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
	} */
	
	public double getGene(int i) {
		return genes[i];
	}
	
	public void setGene(int i, double d) {
		if(d > MAX_VALUE) {
			genes[i] = (d-MIN_VALUE)%(MAX_VALUE-MIN_VALUE)+MIN_VALUE;
		} else if (d < MIN_VALUE) {
			genes[i] = (d-MIN_VALUE)%(MAX_VALUE-MIN_VALUE)+MAX_VALUE;
		} else {
			genes[i] = d;
		}
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public int size() {
        return genes.length;
    }

	
}