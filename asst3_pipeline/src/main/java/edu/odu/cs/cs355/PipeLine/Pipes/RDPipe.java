package edu.odu.cs.cs355.PipeLine.Pipes;

/**
 * Represents a corner pipe connecting Right and Down directions.
 */
public class RDPipe extends Pipe {
    public RDPipe() {
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
        return (direction == Right) || (direction == Down);
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
        if (entryDirection == Right)
            return Down;
        else
            return Right;
    }

    /**
     * Make a copy of the current pipe.
     */
    public Object clone() {
        Pipe p = new RDPipe();
        if (isFilled())
            p.fill();
        return p;
    }
}