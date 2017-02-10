package hr.fer.zemris.optjava.dz12.ga;

import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ematosevic on 06.02.17..
 */
public class GeneticAlgorithm {
    private List<Node> population;
    private int populationSize;
    private int maxGen;
    private double minFitness;
    private int maxDepth;

    public GeneticAlgorithm(int maxGen, int populationSize, double minFitness, int maxDepth){
        this.populationSize = populationSize;
        this.maxGen = maxGen;
        this.minFitness = minFitness;
        this.maxDepth = maxDepth;
        this.population = new ArrayList<>();
    }


    public Node optimize() {
        createRampedHalfAndHalfInitialPopulation(population, maxDepth);
        System.out.println();
        return null;
    }

    public void createRampedHalfAndHalfInitialPopulation(List<Node> population, int maxDepth){
        TreeBuilder tb = new TreeBuilder();
        int numOfOneTypePerDepth = (populationSize / (maxDepth - 1)) / 2;
        for (int i = 2; i <= maxDepth; i++) {
            for (int j = 0; j < numOfOneTypePerDepth; j++) {
                Node growTree = tb.growTree(i);
                Node fullTree = tb.fullTree(i);
                population.add(growTree);
                population.add(fullTree);
            }
        }
    }
}
