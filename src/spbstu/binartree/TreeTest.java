package spbstu.binartree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    private Tree test;

    @Before
    public void init() {
        test = new Tree();
    }

    @After
    public void tearDown() {
        test = null;
    }

    @Test
    public void add() {
        test.Add(5);
        test.Add(1);
        test.Add(9);
        assertEquals("1 5 9", test.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicate() {
        test.Add(1);
        test.Add(1);
    }

    @Test
    public void removeFst() {
        test.Add(8); //root
        test.Add(10); //right subtree
        test.Add(new int[]{5, 2}); // left subtree
        test.Remove(5);
        assertEquals("2 8 10", test.toString());
        assertEquals(test.getNode(8, 2).value, 2);
    }

    @Test
    public void removeScd() {
        test.Add(8); //root
        test.Add(10); //right subtree
        test.Add(new int[]{5, 2, 6, 7}); // left subtree
        test.Remove(5);
        assertEquals("2 6 7 8 10", test.toString());
        assertEquals(test.getNode(8, 2).value, 6);
    }

    @Test
    public void removeThd() {
        test.Add(8); //root
        test.Add(10); //right subtree
        test.Add(new int[]{5, 2, 7, 6}); // left subtree
        assertEquals("2 5 6 7 8 10", test.toString());
        assertEquals(test.getNode(8, 2).value, 5);
        test.Remove(5);
        assertEquals(test.getNode(8, 2).value, 6);
    }
}