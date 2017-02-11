package hr.fer.zemris.optjava.dz12.gui;

import hr.fer.zemris.optjava.dz12.solution.ArtificialAnt;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ematosevic on 07.02.17..
 */
public class ArtificialAntFrame extends JFrame{
    private ArtificialAnt ant;
    private boolean stop = false;

    public ArtificialAntFrame(Node best, ArtificialAnt ant, int[][] finalMap, int width, int height){
        setTitle("Artificial Ant by Erik");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 650);
        setLocation(300, 100);

        setLayout(new BorderLayout());
        MapPanel panel = new MapPanel(ant, finalMap, width, height);
        add(panel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);

        JButton btnStartStop = new JButton("Start");
        southPanel.add(btnStartStop, BorderLayout.WEST);

        JButton btnNextStep = new JButton("Next step");
        southPanel.add(btnNextStep, BorderLayout.CENTER);

        JLabel lblScore = new JLabel("Actions: 0 - Score: 0");
        southPanel.add(lblScore, BorderLayout.EAST);

        TreeExecutor ex = new TreeExecutor(width, height);
        //ex.executeTree(best, finalMap, ant);
        final int[] numActions = {0};

        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (ex.isThereMoreActions()){
                    ex.nextAction(ant, finalMap);
                    numActions[0]++;
                }
                else{
                    ex.executeTree(best, finalMap, ant);
                    ex.nextAction(ant, finalMap);
                    numActions[0]++;
                }
                lblScore.setText("Actions: "+ numActions[0] +" - Score: " + ant.getScore());
                panel.repaint();
            }
        });

        btnStartStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!stop) {
                    timer.start();
                    btnStartStop.setText("Stop");
                    stop = true;
                }
                else{
                    timer.stop();
                    btnStartStop.setText("Start");
                    stop = false;
                }
            }
        });
        btnNextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (ex.isThereMoreActions()){
                    ex.nextAction(ant, finalMap);
                    numActions[0]++;
                }
                else{
                    ex.executeTree(best, finalMap, ant);
                    ex.nextAction(ant, finalMap);
                    numActions[0]++;
                }
                lblScore.setText("Actions: "+ numActions[0] +" - Score: " + ant.getScore());
                panel.repaint();
            }
        });

    }
}
