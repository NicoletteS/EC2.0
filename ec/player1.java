package ec;

import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class player1 implements ContestSubmission
{
	private Random rnd_;
	private ContestEvaluation evaluation_;
	private int evaluations_limit_;
	int popSize = 10;
	

	public player1()
	{
		rnd_ = new Random();
	}

	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Change settings(?)
		if(isMultimodal){
			// Do sth
		}else{
			// Do sth else
		}
	}

	public void run()
	{
		Population pop = new Population(popSize, evaluation_, evaluations_limit_);
		pop.evalPopulation();
		// Run your algorithm here

	
		while (pop.getEvaluation() < evaluations_limit_) {
			pop = Crossover.evolve(pop);
		}
		
/*		
		int evals = 0;
		while(evals<evaluations_limit_){
			// Select parents
			// Apply variation operators and get children
		//	double child[] = ...
	//		Double fitness = evaluation_.evaluate(child);
			evals++;
			// Select survivors
		} */
	}
}
