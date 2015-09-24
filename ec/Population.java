package ec;

import org.vu.contest.ContestEvaluation;

import java.util.Arrays;
import java.util.Random;
import ec.Individual;

public class Population {

	private int populationSize;
	private Individual[] population;
	private ContestEvaluation evaluation_;
	private int evaluations;
	private int evaluations_limit_;
	
	public Population(int populationSize) {
		if (populationSize == 0) {
			
		} else {
			this.population = new Individual[populationSize];
		}
		this.populationSize = populationSize;
	}
	
	public Population(int populationSize, ContestEvaluation eval, int evalLimit) {
		evaluation_ = eval;
		evaluations_limit_ = evalLimit;
		evaluations = 0;
		
		population = new Individual[populationSize];
		
		for(int i=0; i<populationSize;i++) {
			Individual ind = new Individual();
			ind.generateIndividual(evaluation_);
			addIndividual(ind, i);
		}
		
//		Arrays.fill(population, new Individual(evaluation_));
	}
	
	public void addIndividual(Individual ind, int i) {
		population[i] = ind;
	}
	
	public void evalPopulation() {
		for (int i=0; i<population.length && evaluations < evaluations_limit_; i++) {
			population[i].evaluate();
			evaluations++;
		}
	}
	/*
	public void selectParents() {
		Crossover CO = new Crossover(population, evaluation_);
		population = CO.makeParents();
	}
	*/
	public int getEvaluation() {
		return evaluations;
	}
	
	public Individual getIndividual(int i) {
		return population[i];
	}
	
	public Individual getFittest() {
		Individual fittest = population[0];
		for (int i=0; i< size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
	//		fittest = population[population.length-i-1];
		}
		return fittest;
	}
	
	public int size() {
        return population.length;
    }
	
}
