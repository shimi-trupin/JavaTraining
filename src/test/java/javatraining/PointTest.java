package javatraining;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shimi on 17/06/2016.
 */
public class PointTest {
    @Test
    public void getX() throws Exception {
        Point a = new Point(2,4);
        assert( a.x == a.getX() );
    }

    @Test
    public void getY() throws Exception {
        Point a = new Point(2,4);

//        assertEquals(  "y is not  asdokijashbndfgjklbansd ",  a.getY(), 7);
        assertEquals(  "y is not  asdokijashbndfgjklbansd ",  a.getY(), 4);
    }

}