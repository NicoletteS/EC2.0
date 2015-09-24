package classes;

import java.util.Random;
import org.vu.contest.ContestEvaluation;

public class Program {

    public static Random random = new Random(System.currentTimeMillis());
    public static int EVAL_LIMIT = 0;
    private static int EVAL_DONE = 0;
    private static double MUTATE_RATE = 0.03;
    private static int POOL = 5;
    private static double UNIFORM_RATE = 0.8;

    public static Population evolvePopulation(Population pop, int offspring, ContestEvaluation evaluation, boolean isMultimodal, boolean hasStructure, boolean isSeparable) {
        if (EVAL_DONE == 0) {
            EVAL_DONE += pop.size();
        }
        EVAL_DONE += offspring;


        if (isMultimodal) {
            MUTATE_RATE = 1.0;
        }

        Population offspringPopulation = new Population(offspring);
        int startingPoint = 0;

        for (int i = startingPoint; i < offspring; i = i + 1) {
            Individual parent1;
            Individual parent2;
            if (isMultimodal) {
                
                parent1 = Selection.randomSelection(pop);
                parent2 = Selection.randomSelection(pop);

            } else {
                parent1 = Selection.tournamentSelection(pop, POOL);
                do {
                    parent2 = Selection.tournamentSelection(pop, POOL);
                } while (parent1 == parent2);
            }



            Crossover crossover = new Crossover( isMultimodal, EVAL_DONE);
            Mutate mutator = new Mutate(pop.size(), EVAL_DONE);
            Individual child;

            if (isMultimodal) {
                child = crossover.BLXCrossover(parent1, parent2, 0.5);
                if (random.nextDouble() < UNIFORM_RATE) {
                } else {
                    child = mutator.uncorrelatedMutator(child, MUTATE_RATE, 4);
                }

            } else {
                child = crossover.uniformCrossover(parent1, parent2);
                child = mutator.uncorrelatedMutator(child, MUTATE_RATE, 5);
            }


            Double fitness = (Double) evaluation.evaluate(child.getGenes());
            child.setFitness(fitness);
            offspringPopulation.setIndividual(child, i);

        }

        Population fittestOffspring = new Population(pop.size());
        
        Individual[] fittests = offspringPopulation.getFittestIndividuals(fittestOffspring.size());
        for (int i = 0; i < fittestOffspring.size(); i++) {
            fittestOffspring.setIndividual(fittests[i], i);
        }

        return fittestOffspring;
    }
}