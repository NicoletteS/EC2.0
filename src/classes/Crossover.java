package classes;

import static classes.Program.random;

public class Crossover {

    private boolean isMultiModal;
    private int EVAL_DONE;

    Crossover(boolean isMultiModal, int EVAL_DONE) {
        this.isMultiModal = isMultiModal;
        this.EVAL_DONE = EVAL_DONE;
    }

    //BLX Crossover algorithm
    public Individual BLXCrossover(Individual parent1, Individual parent2, double alpha) {

        Individual child = new Individual(isMultiModal);
        Individual x1;
        Individual x2;  

        if (parent1.getFitness() < parent2.getFitness()) {
            x1 = parent1;
            x2 = parent2;
        } else {
            x1 = parent2;
            x2 = parent1;
        }

        for (int i = 0; i < Individual.GENE_LENGTH; i++) {
            double gene;
            double sigma; 
            if (random.nextDouble() <= x2.CROSS_RATE) {
                double rangeMin = x1.getGene(i) - alpha * (x2.getGene(i) - x1.getGene(i));
                double rangeMax = x2.getGene(i) - alpha * (x2.getGene(i) - x1.getGene(i));
                double randomNumber = random.nextDouble();

                gene = rangeMin + (rangeMax - rangeMin) * randomNumber;
                sigma = (x1.getSigma(i) + x2.getSigma(i)) / 2;
                child.CROSS_RATE = (x1.CROSS_RATE + x2.CROSS_RATE)/2;
            } else {
                if (random.nextBoolean()) {
                    gene = x1.getGene(i);
                    sigma = x1.getSigma(i);
                    child.CROSS_RATE = x1.CROSS_RATE;
                } else {
                    gene = x2.getGene(i);
                    sigma = x2.getSigma(i);
                    child.CROSS_RATE = x2.CROSS_RATE;
                }
            }
            child.setGene(i, gene);
            child.setSigma(i, sigma);
        }
        return child;
    }

    //Uniform Crossover algorithm
    public Individual uniformCrossover(Individual parent1, Individual parent2)  {
        Individual child = new Individual(isMultiModal);
        
        for (int i = 0; i < Individual.GENE_LENGTH; i++) {
            if (random.nextBoolean()) {
                child.setGene(i, parent1.getGene(i));
                child.setSigma(i, parent1.getSigma(i));
            } else {
                child.setGene(i, parent2.getGene(i));
                child.setSigma(i, parent2.getSigma(i));
            }
        }
        return child;
    }
}