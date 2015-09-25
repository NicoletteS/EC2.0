package classes;

import static classes.Program.EVAL_LIMIT;
import static classes.Program.random;

public class Mutate {

    private int popSize;
    private double EVAL_DONE;

    Mutate(int popSize, double EVAL_DONE) {
        this.popSize = popSize;
        this.EVAL_DONE = EVAL_DONE;
    }

    public Individual uncorrelatedMutator(Individual ind, double MUTATE_RATE, int alpha) {
        for (int i = 0; i < Individual.GENE_LENGTH; i++) {
            if (random.nextDouble() <= MUTATE_RATE) {
                double sigma;

                sigma = 1.0 - ((double) EVAL_DONE / (double) EVAL_LIMIT);
                sigma = Math.pow(sigma, alpha);
                ind.setSigma(i, sigma);
                double gene = ind.getGene(i) + ind.ni[i] * ind.getSigma(i);
                ind.setGene(i, gene);
            }
        }

        return ind;
    }
}