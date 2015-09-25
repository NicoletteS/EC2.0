package classes;

import static classes.Program.random;
import java.util.ArrayList;

public class Selection {
	
    public Selection() {
    }

    public static Individual randomSelection(Population pop) {
        int randomNumber = random.nextInt(pop.size());
        return pop.getIndividual(randomNumber);
    }

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