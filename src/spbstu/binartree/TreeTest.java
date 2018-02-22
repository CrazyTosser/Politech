package spbstu.binartree;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    private Tree test;

    @After
    public void tearDown() {
        test = null;
    }

    @Test
    public void add() {
        test = new Tree(5);
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
        test = new Tree(1);
        test.add(1);
    }

    @Test
    public void removeFst() {
        test = new Tree(8); //root
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
        test = new Tree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2, 7, 6}); // left subtree
        assertEquals("2 5 6 7 8 10", test.toString());
        test.remove(5);
        assertEquals(test.printTree(), "ROOT:8\n" +
                "Left for 8 --> 2\n" +
                "Right for 2 --> 7\n" +
                "Left for 7 --> 6\n" +
                "Right for 8 --> 10\n");
    }

    @Test
    public void removeThd() {
        test = new Tree(10); //root
        test.add(new int[]{12, 11, 14}); //right subtree
        test.add(new int[]{7, 9, 6, 3, 4}); // left subtree
        assertEquals("3 4 6 7 9 10 11 12 14", test.toString());
        test.remove(6);
        System.out.println(test.printTree());
        assertEquals(test.printTree(), "ROOT:10\n" +
                "Left for 10 --> 7\n" +
                "Left for 7 --> 3\n" +
                "Right for 3 --> 4\n" +
                "Right for 7 --> 9\n" +
                "Right for 10 --> 12\n" +
                "Left for 12 --> 11\n" +
                "Right for 12 --> 14\n");
    }
}