package edu.odu.cs.cs355;

import java.util.Iterator;

public class Array2D implements Iterable<Integer> {

    // Do not change or add to the data members.
    private int[] data;
    private int width;
    private int height;


    public Array2D(int width, int height) {
        //*** Your code here.
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
    }


    public int size() {
        return width * height;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public int get(int i, int j) {
        //*** Your code here.
        if (i < 0 || i >= width || j < 0 || j >= height) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[i * height + j];
    }

    public void set(int i, int j, int value) {
        //*** Your code here.
        if (i < 0 || i >= width || j < 0 || j >= height) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        data[i * height + j] = value;
    }



    // Do not change anything below this line.
    private class ArrayIterator implements Iterator<Integer> {

        private int index;

        public ArrayIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < width * height;
        }

        @Override
        public Integer next() {
            int result = data[index];
            ++index;
            return result;
        }

    }

    @Override
    public Iterator<Integer> iterator() {
        return new ArrayIterator();
    }


}
