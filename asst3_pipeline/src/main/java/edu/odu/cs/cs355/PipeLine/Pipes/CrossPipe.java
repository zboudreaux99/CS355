package edu.odu.cs.cs355.PipeLine.Pipes;

/**
 * Represents two pipes crossing one another.
 */
public class CrossPipe extends Pipe {
    public CrossPipe() {
        super();
    }

    /**
     * Is the pipe open at the indicated end?
     *
     * @param direction Which end are we asking about?
     * @return True if liquid can enter or leave a pipe from the indicated
     *         direction.
     */
    public boolean isOpenAtThisEnd(int direction) {
        return true;
    }

    /**
     * From what direction will the liquid emerge?
     *
     * @param entryDirection The direction from which liquid is entering
     *                       the pipe.
     * @return The direction at which the liquid will emerge.
     */
    public int emergeDirection(int entryDirection)
    // pre: isOpenAtThisEnd(entryDirection)
    {
        return (entryDirection + 2) % 4;
    }

    /**
     * Make a copy of the current pipe.
     */
    public Object clone() {
        Pipe p = new CrossPipe();
        if (isFilled())
            p.fill();
        return p;
    }
}
