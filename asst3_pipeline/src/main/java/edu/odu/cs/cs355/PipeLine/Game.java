package edu.odu.cs.cs355.PipeLine;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class Game extends JFrame {
    public Game() {
        getContentPane().add(new GameBoard());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        /*
         * addMouseListener
         * (new MouseAdapter()
         * {public void MouseClicked(MouseEvent m)
         * {
         * System.out.println ("mouseclicked " + m.getPoint());
         * }
         * });
         */
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 350);
    }

    public static void main(String args[]) {
        Game window = new Game();
        window.setTitle("CS 355 - Spring 2025");
        window.pack();
        window.setVisible(true);
    }

}
