package edu.odu.cs.cs355.PipeLine;

import java.awt.*;

import edu.odu.cs.cs355.PipeLine.Pipes.Pipe;

/**
 * The tile in the status line, used to indicate the next pipe.
 */
public class StatusTile extends Tile {

    public StatusTile() {
        super();
    }

    public void paint(Graphics g) {
        Pipe p = GameState.nextPipe();
        if (p != getPipe())
            setPipe(p);
        super.paint(g);
    }

    protected void selected() {
    }

}
