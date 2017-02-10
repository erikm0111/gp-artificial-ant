package hr.fer.zemris.optjava.dz12.ga.selection;

import hr.fer.zemris.optjava.dz12.solution.GANodeSolution;
import hr.fer.zemris.optjava.dz12.solution.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ematosevic on 10.02.17..
 */
public class KTournamentSelection implements ISelection {
    private int k;
    private Random rand;

    public KTournamentSelection(int k){
        this.k = k;
        this.rand = new Random();
    }

    @Override
    public GANodeSolution selectParent(List<GANodeSolution> population) {
        List<GANodeSolution> randomlyChosen = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            randomlyChosen.add(population.get(rand.nextInt(population.size())));
        }
        Collections.sort(randomlyChosen);
        return randomlyChosen.get(0);
    }
}
