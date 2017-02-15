package hr.fer.zemris.optjava.dz12;

import hr.fer.zemris.optjava.dz12.ga.GeneticAlgorithm;
import hr.fer.zemris.optjava.dz12.ga.crossover.ICrossover;
import hr.fer.zemris.optjava.dz12.ga.crossover.SubTreeReplacementCrossover;
import hr.fer.zemris.optjava.dz12.ga.mutation.GrowMutation;
import hr.fer.zemris.optjava.dz12.ga.mutation.IMutation;
import hr.fer.zemris.optjava.dz12.ga.selection.ISelection;
import hr.fer.zemris.optjava.dz12.ga.selection.KTournamentSelection;
import hr.fer.zemris.optjava.dz12.gui.ArtificialAntFrame;
import hr.fer.zemris.optjava.dz12.solution.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ematosevic on 05.02.17..
 */
public class AntTrailGA {
    public static final int INITIAL_MAX_DEPTH = 6;
    public static final int MAX_DEPTH_LIMIT = 20;
    public static final int MAX_NODES_LIMIT = 200;
    public static final int K_SELECTION = 7;

    public static void main(String[] args) throws Exception {
        List<String> lines = new ArrayList<>();
        int[][] map = null;

        String pathMap = args[0];
        int maxGen = Integer.parseInt(args[1]);
        int populationSize = Integer.parseInt(args[2]);
        double minFitness = Double.parseDouble(args[3]);
        String pathSolution = args[4];
//        String pathMap = "santa-fe-trail.txt";
//        int maxGen = 100;
//        int populationSize = 500;
//        double minFitness = 70;

        File file = new File(pathMap);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        String dimStr = reader.readLine();
        while((line = reader.readLine()) != null){
            if(line.isEmpty()){
                break;
            }
            lines.add(line);
        }
        int width = Integer.parseInt(dimStr.split("x")[0]);
        int height = Integer.parseInt(dimStr.split("x")[1]);

        map = new int[width][height];
        parseMap(map, lines, width, height);
        int[][] finalMap = map;
        ArtificialAnt ant = new ArtificialAnt(0, 0, Direction.EAST);

        TreeOperation tb = new TreeOperation();
        TreeExecutor executor = new TreeExecutor(width, height);
        ISelection selection = new KTournamentSelection(K_SELECTION);
        IMutation mutation = new GrowMutation(tb, MAX_DEPTH_LIMIT, MAX_NODES_LIMIT);
        Utils utils = new Utils();
        ICrossover crossover = new SubTreeReplacementCrossover(tb, MAX_DEPTH_LIMIT, MAX_NODES_LIMIT, utils);
        GeneticAlgorithm ga = new GeneticAlgorithm(map, width, height, ant, executor, maxGen, populationSize, minFitness, INITIAL_MAX_DEPTH, selection, mutation, crossover, utils);
        Node best = ga.optimize();

        //System.out.println(tb.treeToText(best));
        PrintWriter pw = new PrintWriter(new FileWriter(pathSolution));
        pw.println(tb.treeToText(best));
        pw.close();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArtificialAntFrame frame = new ArtificialAntFrame(best, ant, finalMap, width, height);
            }
        });

    }

    /**
     * Method that parses string representation map into int 2D array.
     * "." => -1
     * "0" => 0
     * "1" => 1
     * @param map 2D int array that has to be populated
     * @param lines list of strings representing the map
     * @param width width of map
     * @param height height of map
     */
    public static void parseMap(int[][] map, List<String> lines, int width, int height) throws Exception {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            String[] symbols = line.split("");
            for (int j = 0; j < symbols.length; j++) {
                String symbol = symbols[j];
                switch (symbol){
                    case ".":
                        map[i][j] = -1;
                        break;
                    case "0":
                        map[i][j] = -1;
                        break;
                    case "1":
                        map[i][j] = 1;
                        break;
                    default:
                        throw new Exception("Invalid symbol in map!");
                }
            }
        }
    }
}
