package edu.odu.cs.cs355.PipeLine.Pipes;

/**
 * Represents a corner pipe connecting Left and Down directions.
 */
public class LDPipe extends Pipe {
    public LDPipe() {
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
        return (direction == Left) || (direction == Down);
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
        if (entryDirection == Left)
            return Down;
        else
            return Left;
    }

    /**
     * Make a copy of the current pipe.
     */
    public Object clone() {
        Pipe p = new LDPipe();
        if (isFilled())
            p.fill();
        return p;
    }
}