package spbstu.binartree;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    private NewTree test;

    @After
    public void tearDown() {
        test = null;
    }

    @Test
    public void add() {
        test = new NewTree(5);
        test.add(1);
        test.add(9);
        assertEquals("1 5 9", test.toString());
        assertEquals(test.printTree(),
                "ROOT:5\n" +
                        "Left for 5 --> 1\n" +
                        "Right for 5 --> 9\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicate() {
        test = new NewTree(1);
        test.add(1);
    }

    @Test
    public void removeFst() {
        test = new NewTree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2}); // left subtree
        assertEquals(test.printTree(),
                "ROOT:8\n" +
                        "Left for 8 --> 5\n" +
                        "Left for 5 --> 2\n" +
                        "Right for 8 --> 10\n");
        test.remove(5);
        assertEquals("2 8 10", test.toString());
        assertEquals(test.printTree(),
                "ROOT:8\n" +
                        "Left for 8 --> 2\n" +
                        "Right for 8 --> 10\n");
    }

    @Test
    public void removeScd() {
        test = new NewTree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2, 6, 7}); // left subtree
        test.remove(5);
        assertEquals("2 6 7 8 10", test.toString());
        assertEquals(test.printTree(), "ROOT:8\n" +
                "Left for 8 --> 6\n" +
                "Left for 6 --> 2\n" +
                "Right for 6 --> 7\n" +
                "Right for 8 --> 10\n");
    }

    @Test
    public void removeThd() {
        test = new NewTree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2, 7, 6}); // left subtree
        assertEquals("2 5 6 7 8 10", test.toString());
        test.remove(5);
        System.out.println(test.printTree());
    }
}