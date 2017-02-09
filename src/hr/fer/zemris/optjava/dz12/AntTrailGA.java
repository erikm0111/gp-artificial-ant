package hr.fer.zemris.optjava.dz12;

import hr.fer.zemris.optjava.dz12.gui.ArtificialAntFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ematosevic on 05.02.17..
 */
public class AntTrailGA {
    public static void main(String[] args) throws Exception {
        List<String> lines = new ArrayList<>();
        int[][] map = null;

        String pathMap = args[0];
        int maxGen = Integer.parseInt(args[1]);
        int populationSize = Integer.parseInt(args[2]);
        double minFitness = Double.parseDouble(args[3]);
        String pathSolution = args[4];

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArtificialAntFrame frame = new ArtificialAntFrame(finalMap, width, height);
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
                        map[i][j] = 0;
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
