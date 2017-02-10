package hr.fer.zemris.optjava.dz12;

import hr.fer.zemris.optjava.dz12.ga.GeneticAlgorithm;
import hr.fer.zemris.optjava.dz12.ga.selection.ISelection;
import hr.fer.zemris.optjava.dz12.ga.selection.KTournamentSelection;
import hr.fer.zemris.optjava.dz12.gui.ArtificialAntFrame;
import hr.fer.zemris.optjava.dz12.solution.*;
import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ematosevic on 05.02.17..
 */
public class AntTrailGA {
    public static final int MAX_DEPTH = 6;

    public static void main(String[] args) throws Exception {
        List<String> lines = new ArrayList<>();
        int[][] map = null;

//        String pathMap = args[0];
//        int maxGen = Integer.parseInt(args[1]);
//        int populationSize = Integer.parseInt(args[2]);
//        double minFitness = Double.parseDouble(args[3]);
//        String pathSolution = args[4];
        String pathMap = "santa-fe-trail.txt";
        int maxGen = 1000;
        int populationSize = 1000;
        double minFitness = 70;

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

        TreeExecutor executor = new TreeExecutor(width, height);
        ISelection selection = new KTournamentSelection(7);
        GeneticAlgorithm ga = new GeneticAlgorithm(map, width, height, ant, executor, maxGen, populationSize, minFitness, MAX_DEPTH, selection);
        Node best = ga.optimize();


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArtificialAntFrame frame = new ArtificialAntFrame(best, ant, finalMap, width, height);
            }
        });
//        TreeBuilder tb = new TreeBuilder();
//        Node root = tb.createRandomTree(3);
//        ArtificialAnt ant = new ArtificialAnt(0,0, Direction.EAST);
//        Executor ex = new Executor(ant, map, width, height);
//        ex.executeTree(root);
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
