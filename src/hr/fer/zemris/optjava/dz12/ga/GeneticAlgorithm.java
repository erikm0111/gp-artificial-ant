package hr.fer.zemris.optjava.dz12.ga;

import hr.fer.zemris.optjava.dz12.ga.crossover.ICrossover;
import hr.fer.zemris.optjava.dz12.ga.mutation.IMutation;
import hr.fer.zemris.optjava.dz12.ga.selection.ISelection;
import hr.fer.zemris.optjava.dz12.solution.*;
import sun.reflect.generics.tree.Tree;

import javax.rmi.CORBA.Util;
import java.io.IOException;
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
    private IMutation mutation;
    private ICrossover crossover;
    private Utils utils;

    public GeneticAlgorithm(int[][] map , int width, int height, ArtificialAnt ant, TreeExecutor executor, int maxGen
            , int populationSize, double minFitness, int maxDepth, ISelection selection, IMutation mutation, ICrossover crossover, Utils utils){
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
        this.mutation = mutation;
        this.crossover = crossover;
        this.utils = utils;
    }


    public Node optimize() throws IOException, ClassNotFoundException {
        createRampedHalfAndHalfInitialPopulation(population, maxDepth);
        evaluate(population);

        int numGen = 0;
        while (numGen < maxGen) {
            List<GANodeSolution> nextGeneration = new ArrayList<>();
            Collections.sort(population);
            //printMaxNodesAndDepth(population);
            printHowManyHaveMaxFitness(population);
            System.out.println("Gen: " + numGen + ", best fitness: " + population.get(0).getFitness() + ", worst fitness: " + population.get(population.size() - 1).getFitness());

            createNextGeneration(nextGeneration, population);

            population = new ArrayList<>(nextGeneration);
            evaluate(population);
            numGen++;
        }

        Collections.sort(population);
        System.out.println("Best fitness: " + population.get(0).getFitness());
        System.out.println("Worst fitness: " + population.get(populationSize - 1).getFitness());
        return population.get(0).getNode();
    }

    private void printHowManyHaveMaxFitness(List<GANodeSolution> population) {
        int maxFitness = population.get(0).getFitness();
        int i = 0;
        for (GANodeSolution sol : population){
            if (sol.getFitness() == maxFitness){
                ++i;
            }
        }
        System.out.println("Percent with max fitness: " + ((double) i / population.size()));
    }

    private void createNextGeneration(List<GANodeSolution> nextGeneration, List<GANodeSolution> population) throws IOException, ClassNotFoundException {
        nextGeneration.add(population.get(0));
        while (nextGeneration.size() < populationSize) {
            double p = rand.nextDouble();
            if (p <= REPRODUCTION_PROB) {                                                       // REPRODUCTION
                GANodeSolution selected = selection.selectParent(population);
                nextGeneration.add(selected);
            } else if (p <= REPRODUCTION_PROB + MUTATION_PROB) {                                // MUTATION
                boolean isValid = false;
                while (!isValid){
                    GANodeSolution selected = selection.selectParent(population);
                    Node copy = utils.copy(selected.getNode());
                    GANodeSolution copySolution = new GANodeSolution(copy);
                    isValid = mutation.mutate(copySolution);
                    if (isValid) {
                        nextGeneration.add(copySolution);
                    }
                    else{
    //                    nextGeneration.add(selected);
                    }
                }
            } else {                                                                            // CROSSOVER
                boolean isValid = false;
                while (!isValid) {
                    GANodeSolution first = selection.selectParent(population);
                    GANodeSolution second = selection.selectParent(population);
                    GANodeSolution firstCopy = new GANodeSolution(utils.copy(first.getNode()));
                    GANodeSolution secondCopy = new GANodeSolution(utils.copy(second.getNode()));
                    isValid = crossover.crossover(firstCopy, secondCopy);
                    if (isValid) {
                        nextGeneration.add(firstCopy);
                        nextGeneration.add(secondCopy);
                    } else {
//                    nextGeneration.add(first);
//                    nextGeneration.add(second);
                    }
                }

            }
        }
    }

    private void printMaxNodesAndDepth(List<GANodeSolution> population) {
        int maxNodes = 0;
        int maxDepth = 0;
        TreeOperation tb = new TreeOperation();
        for (int i = 0; i < populationSize; i++) {
            int numNodes = tb.countNodesInTree(population.get(i).getNode());
            //System.out.print(numNodes + " ");
            int numDepth = tb.getTreeDepth(population.get(i).getNode());
            if (numNodes > maxNodes){
                maxNodes = numNodes;
            }
            if (numDepth > maxDepth){
                maxDepth = numDepth;
            }
        }
        //System.out.println();
        System.out.println("max depth: " + maxDepth +", max nodes: " + maxNodes);
    }

    private void evaluate(List<GANodeSolution> population) {
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
    }

    public void createRampedHalfAndHalfInitialPopulation(List<GANodeSolution> population, int maxDepth){
        TreeOperation tb = new TreeOperation();
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
