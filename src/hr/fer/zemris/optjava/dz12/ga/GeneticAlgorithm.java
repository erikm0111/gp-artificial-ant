package hr.fer.zemris.optjava.dz12.ga;

import hr.fer.zemris.optjava.dz12.ga.selection.ISelection;
import hr.fer.zemris.optjava.dz12.solution.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ematosevic on 06.02.17..
 */
public class GeneticAlgorithm {
    private static final double CROSSOVER_PROB = 0.85;
    private static final double MUTATION_PROB = 0.14;
    private static final double REPRODUCTION_PROB = 0.01;
    private List<GANodeSolution> population;
    private int populationSize;
    private int maxGen;
    private double minFitness;
    private int maxDepth;
    private TreeExecutor executor;
    private int[][] map;
    private ArtificialAnt ant;
    private int width;
    private int height;
    private Random rand;
    private ISelection selection;

    public GeneticAlgorithm(int[][] map , int width, int height, ArtificialAnt ant, TreeExecutor executor, int maxGen
            , int populationSize, double minFitness, int maxDepth, ISelection selection){
        this.populationSize = populationSize;
        this.maxGen = maxGen;
        this.minFitness = minFitness;
        this.maxDepth = maxDepth;
        this.executor = executor;
        this.population = new ArrayList<>();
        this.ant = ant;
        this.width = width;
        this.height = height;
        this.map = map;
        this.rand = new Random();
        this.selection = selection;
    }


    public Node optimize() {
        createRampedHalfAndHalfInitialPopulation(population, maxDepth);
        for (GANodeSolution sol : population){
            ArtificialAnt antCopy = new ArtificialAnt(ant);
            int[][] mapCopy = new int[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    mapCopy[i][j] = map[i][j];
                }
            }
            int fitness = executor.evaluate(sol.getNode(), mapCopy, antCopy);
            sol.setFitness(fitness);
        }

        List<GANodeSolution> nextGeneration = new ArrayList<>();
        while (nextGeneration.size() < populationSize){
            double p = rand.nextDouble();
            if (p <= REPRODUCTION_PROB){
                // reproduction
                GANodeSolution selected = selection.selectParent(population);
                nextGeneration.add(selected);
            }
            else if (p <= REPRODUCTION_PROB + MUTATION_PROB){
                // mutation

            }
            else{
                // crossover
                
            }
        }

//        Collections.sort(population);
//        System.out.println(population.get(0).getFitness());
//        System.out.println(population.get(populationSize - 1).getFitness());
        return population.get(0).getNode();
    }

    public void createRampedHalfAndHalfInitialPopulation(List<GANodeSolution> population, int maxDepth){
        TreeBuilder tb = new TreeBuilder();
        int numOfOneTypePerDepth = (populationSize / (maxDepth - 1)) / 2;
        for (int i = 2; i <= maxDepth; i++) {
            for (int j = 0; j < numOfOneTypePerDepth; j++) {
                Node growTree = tb.growTree(i);
                Node fullTree = tb.fullTree(i);
                population.add(new GANodeSolution(growTree));
                population.add(new GANodeSolution(fullTree));
            }
        }
    }
}
