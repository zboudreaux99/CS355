package edu.odu.cs.cs355.PipeLine.Pipes;

/**
 * Represents a pipe that may be filled or empty, and may
 * connect some combinations of the directions Up, Down, Left, and Right.
 *
 */
public abstract class Pipe implements Cloneable {
    /**
     * Is liquid flowing through the pipe?
     */
    private boolean filled;

    /**
     * Symbolic names for directions.
     */
    public final static int Up = 0;
    public final static int Left = 1;
    public final static int Down = 2;
    public final static int Right = 3;

    /**
     * Default pipe initialization.
     * Pipes are initially empty.
     */
    public Pipe() {
        filled = false;
    }

    /**
     * Is the pipe open at the indicated end?
     *
     * @param direction Which end are we asking about?
     * @return True if liquid can enter or leave a pipe from the indicated
     *         direction.
     */
    public abstract boolean isOpenAtThisEnd(int direction);

    /**
     * From what direction will the liquid emerge?
     *
     * @param entryDirection The direction from which liquid is entering
     *                       the pipe.
     * @return The direction at which the liquid will emerge.
     */
    public abstract int emergeDirection(int entryDirection);
    // pre: isOpenAtThisEnd(entryDirection)

    /**
     * Is there liquid flowing through the pipe?
     *
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Indicate that liquid is now in the pipe.
     *
     */
    public void fill() {
        filled = true;
    }

    /**
     * Indicate that no liquid is now in the pipe.
     *
     */
    public void empty() {
        filled = false;
    }

    /**
     * Make a copy of the current pipe.
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() not yet implemented");
    }

}
