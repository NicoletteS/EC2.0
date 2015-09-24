package classes;

import static classes.Program.random;
import java.util.ArrayList;

public class Selection {
	
	private static int storedIndex = -1;
	
    public Selection() {
    }

    public static Individual randomSelection(Population pop) {
        int randomNumber = random.nextInt(pop.size());
        return pop.getIndividual(randomNumber);
    }

    public Individual RankingSelection(Population pop) {
        return RankingSelection(pop, true);
    }

    public static Individual RankingSelection(Population pop, boolean storeIndex){

        Individual[] fittests = pop.getFittestIndividuals(pop.size());
        double[] probabilities = new double[pop.size()];
        double sum = 0;

        Individual[] ranked = new Individual[pop.size()];
        for (int i = 0; i < pop.size(); i++) {
            ranked[i] = fittests[pop.size() - i - 1];
        }

        for (int i = 0; i < pop.size(); i++) {
            //exponential method
            probabilities[i] = (1 - 1 / Math.exp(i + 1));
            sum += probabilities[i];
        }


        double sum2 = 0;
        for (int i = 0; i < pop.size(); i++) {
            probabilities[i] /= sum;
            sum2 += probabilities[i];
        }

        double randomNumber = random.nextDouble() * sum2;
        int idx;
        for (idx = 0; idx < pop.size() && randomNumber >= 0; ++idx) {
            randomNumber -= probabilities[idx];
        }

        if (storeIndex) {
            storedIndex = idx - 1;
        }

        return ranked[idx - 1];
    }

    public static Individual similarSelection(Population pop, Individual i1) {
        double distance[] = new double[pop.size()];
        double min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < pop.size(); i++) {
            if (min > distance[i] && distance[i] != 0.0) {
                min = distance[i];
                index = i;
            }
        }
        return pop.getIndividual(index);
    }

//    private static double minFitness(ArrayList<Individual> pop){
//        double min = Integer.MAX_VALUE;
//
//        for (int i = 0; i < pop.size(); i++) {
//            if (pop.get(i).getFitness() < min) {
//                min = pop.get(i).getFitness();
//            }
//        }
//
//        return min;
//    }

    public static Individual tournamentSelection(Population pop, int number_tournament_candidates) {
        ArrayList<Individual> selected = new ArrayList<Individual>();
        Individual best = null;
        int i = 0;
        while (i < number_tournament_candidates) {
            Individual sel = randomSelection(pop);
            if (!selected.contains(sel)) {
                selected.add(sel);
                i++;
                if (best == null) {
                    best = sel;
                } else {
                    if (best.getFitness() < sel.getFitness()) {
                        best = sel;
                    }
                }
            }
        }
        return best;
    }

}