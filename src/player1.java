import classes.Program;
import classes.Individual;
import classes.Population;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player1 implements ContestSubmission {

    private Random rnd_;
    private ContestEvaluation evaluation_;
    private int evaluations_limit_;
    private int populationSize = 100;
    private int offspringSize = 500;
    private boolean isMultimodal;
    private boolean hasStructure;
    private boolean isSeparable;

    public player1() {
        rnd_ = new Random();
    }

    public void setSeed(long seed) {
        // Set seed of algorithms random process
        rnd_.setSeed(seed);
    }

    public void setEvaluation(ContestEvaluation evaluation) {
        // Set evaluation problem used in the run
        evaluation_ = evaluation;

        // Get evaluation properties
        Properties props = evaluation.getProperties();
        // Property keys depend on specific evaluation
        // E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        // Get evaluation properties
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
        isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        hasStructure = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
        isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        // Change settings(?)
        if (isMultimodal) {
            // Do sth
        } else {
            // Do sth else
        }
        // Do sth with property values, e.g. specify relevant settings of your algorithm
    }

    public void run() {
    		//Start population
            Population pop = new Population(populationSize, isMultimodal, evaluation_);
            
            //Give evaluation limits = 100000
            Program.EVAL_LIMIT = evaluations_limit_;
            //Amount of evaluation
            int evals = populationSize+offspringSize;

            //Evolve population. It is a generational algorithm.
            while (evals <= evaluations_limit_ && offspringSize !=0) {
                pop = Program.evolvePopulation(pop, offspringSize, evaluation_, isMultimodal, hasStructure, isSeparable);

                if (isMultimodal) {
                        Population fittestOffspring = new Population(populationSize);
                        
                        //Get fittest individuals for offspring population
                        Individual[] fittests = pop.getFittestIndividuals(fittestOffspring.size());
                        for (int j = 0; j < fittestOffspring.size(); j++) {
                            fittestOffspring.setIndividual(fittests[j], j);
                        }
                        pop = fittestOffspring;
                }
                offspringSize = Math.min(offspringSize, evaluations_limit_-evals);
                evals += offspringSize;
            }
    }
}