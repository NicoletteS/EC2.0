package classes;

import java.util.Random;
import org.vu.contest.ContestEvaluation;
import classes.MergeSort;

public class Population {

    private Individual[] population;
    private int popSize;
    private boolean merge = false;
    static public Random random = new Random(System.currentTimeMillis());
	private int evaluations;
//    public double nf1=0;
//    public double nf2=0;
//    public double ns1=0;
//    public double ns2=0;
    
    public Population(int popSize) {
        if(popSize == 0){
            
        }
        this.population = new Individual[popSize];
        
        this.popSize = popSize;
    }

    public Population(int popSize, boolean isMultimodal, ContestEvaluation eval) {

            this.popSize = popSize;
            population = new Individual[popSize];
            for (int i = 0; i < popSize; i++) {
                Individual ind = new Individual(isMultimodal);
                ind.generateIndividual(eval);
                setIndividual(ind, i);
            }
        
    }

    public void setIndividual(Individual ind, int index) {
            population[index] = ind;
        
    }

    public Individual getIndividual(int index) {
            return population[index];
        
    }
    
	public int getEvaluation() {
		return evaluations;
	}

    public Individual[] getFittestIndividuals(int number) {
    	if (!merge) {
            MergeSort.mergeSort(population);
        }
        Individual[] fittest = new Individual[number];
        for (int i = 0; i < number; i++) {
            fittest[i] = population[population.length-i-1];
        }
        return fittest;
    }

    public int size() {
        return popSize;
    }
}