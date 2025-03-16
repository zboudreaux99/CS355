package edu.odu.cs.cs355.PipeLine.Pipes;

import java.util.ArrayList;

/**
 * A collection of one each of all the available kinds of pipes.
 *
 */
public class PipeKit {
    /**
     * Holds the pipes.
     */
    private ArrayList<Pipe> thePipes;

    /**
     * Builds the pipekit, placing each of the different kinds of pipes
     * within it.
     */
    public PipeKit() {
        // Initialize the vector
        thePipes = new ArrayList<Pipe>();

        // Add one of each kind of pipe
        thePipes.add(new HorizontalPipe());
        thePipes.add(new VerticalPipe());
        thePipes.add(new CrossPipe());
    }

    /**
     * How many pipes in this kit?
     *
     */
    public int size() {
        return thePipes.size();
    }

    /**
     * Select a pipe from the kit.
     *
     */
    public Pipe getPipe(int index)
    // pre: index >= 0 && index < size()
    {
        return thePipes.get(index);
    }

}
