package hr.fer.zemris.optjava.dz12.ga;

import hr.fer.zemris.optjava.dz12.solution.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ematosevic on 06.02.17..
 */
public class GeneticAlgorithm {
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

    public GeneticAlgorithm(int[][] map , int width, int height, ArtificialAnt ant, TreeExecutor executor, int maxGen, int populationSize, double minFitness, int maxDepth){
        this.populationSize = populationSize;
        this.maxGen = maxGen;
        this.minFitness = minFitness;
        this.maxDepth = maxDepth;
        this.executor = executor;
        this.population = new ArrayList<>();
        this.ant = ant;
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.map[i][j] = map[i][j];
            }
        }
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
        Collections.sort(population);
        System.out.println(population.get(0).getFitness());
        System.out.println(population.get(populationSize - 1).getFitness());
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
