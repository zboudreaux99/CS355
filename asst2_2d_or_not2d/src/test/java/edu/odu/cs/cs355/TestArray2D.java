/**
 * 
 */
package edu.odu.cs.cs355;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author zeil
 *
 */
public class TestArray2D {

    @Test
    public void testConstructor() {
        Array2D arr = new Array2D(3, 2);
        assertThat(arr.size(), is(6));
        assertThat(arr.getWidth(), is(3));
        assertThat(arr.getHeight(), is(2));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 2; ++j)
                assertThat(arr.get(i, j), is(0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.get(-1, 1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.get(3, 1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.get(1, -1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.get(1, 2));

        assertThat(arr, contains(0, 0, 0, 0, 0, 0));
    }

    @Test
    public void testSet() {
        Array2D arr = new Array2D(3, 2);
        assertThat(arr.size(), is(6));
        assertThat(arr.getWidth(), is(3));
        assertThat(arr.getHeight(), is(2));

        int counter = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 2; ++j) {
                arr.set(i, j, counter);
                ++counter;
            }

        counter = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 2; ++j) {
                assertThat(arr.get(i, j), is(counter));
                ++counter;
            }

        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(-1, 1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(3, 1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(1, -1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(1, 2, 10));

        Integer[] expected = { 0, 1, 2, 3, 4, 5 };

        assertIterableEquals(arr, Arrays.asList(expected));

    }

    @Test
    public void testSetLarger() {
        Array2D arr = new Array2D(200, 300);
        assertThat(arr.size(), is(200*300));
        assertThat(arr.getWidth(), is(200));
        assertThat(arr.getHeight(), is(300));

        ArrayList<Integer> expected = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < 200; ++i)
            for (int j = 0; j < 300; ++j) {
                int v = rand.nextInt();
                arr.set(i, j, v);
                expected.add(v);
            }

        int counter = 0;
        for (int i = 0; i < 200; ++i)
            for (int j = 0; j < 300; ++j) {
                assertThat(arr.get(i, j), is(expected.get(counter)));
                ++counter;
            }

        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(-1, 1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(200, 1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(1, -1, 10));
        assertThrows(IndexOutOfBoundsException.class,
                () -> arr.set(1, 300, 10));

        assertIterableEquals(arr, expected);

    }


}
