package edu.odu.cs.cs355.PipeLine;

import java.util.Random;

import edu.odu.cs.cs355.PipeLine.Pipes.HorizontalPipe;
import edu.odu.cs.cs355.PipeLine.Pipes.Pipe;
import edu.odu.cs.cs355.PipeLine.Pipes.PipeKit;

import java.awt.Component;

class GameState {
    private static PipeKit kit = new PipeKit();
    private static Pipe theNextPipe = null;

    private static Random rnd = new Random();
    private static Component nextPipeGraphic;
    private static Scored game;

    public GameState(Component nextPipeTile, Scored theGame) {
        nextPipeGraphic = nextPipeTile;
        game = theGame;
    }

    public static Pipe nextPipe() {
        if (theNextPipe == null)
            useNextPipe();
        return theNextPipe;
    }

    public static void rescore() {
        if (game != null)
            game.computeScore();
    }

    public static Pipe useNextPipe() {
        Pipe p = theNextPipe;
        theNextPipe = kit.getPipe(Math.abs(rnd.nextInt()) % kit.size());
        try {
            theNextPipe = (Pipe) theNextPipe.clone();
        } catch (CloneNotSupportedException exc) {
            theNextPipe = new HorizontalPipe();
        }
        nextPipeGraphic.repaint();
        return p;
    }
}
