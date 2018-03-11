package spbstu.binartree;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        test.add(null);
        assertEquals("1 5 9", test.toString());
        assertEquals(test.printTree(),
                "ROOT:5\n" +
                        "Left for 5 --> 1\n" +
                        "Right for 5 --> 9\n");
    }

    @Test
    public void removeLeft() {
        test = new Tree(8);
        test.add(10);
        test.add(new int[]{5, 2});
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
    public void removeLeftSub() {
        test = new Tree(8);
        test.add(10);
        test.add(new int[]{5, 3, 7, 6, 4, 2});
        assertEquals("2 3 4 5 6 7 8 10", test.toString());
        test.remove(5);
        assertEquals(test.printTree(), "ROOT:8\n" +
                "Left for 8 --> 4\n" +
                "Left for 4 --> 3\n" +
                "Left for 3 --> 2\n" +
                "Right for 4 --> 7\n" +
                "Left for 7 --> 6\n" +
                "Right for 8 --> 10\n");
    }

    @Test
    public void removeRightSub() {
        test = new Tree(10);
        test.add(new int[]{12, 11, 14});
        test.add(new int[]{7, 9, 6, 3, 4});
        assertEquals("3 4 6 7 9 10 11 12 14", test.toString());
        test.remove(6);
        assertEquals(test.printTree(), "ROOT:10\n" +
                "Left for 10 --> 7\n" +
                "Left for 7 --> 3\n" +
                "Right for 3 --> 4\n" +
                "Right for 7 --> 9\n" +
                "Right for 10 --> 12\n" +
                "Left for 12 --> 11\n" +
                "Right for 12 --> 14\n");
    }

    @Test
    public void recreateTree() {
        test = new Tree(5);
        test.add(new int[]{6, 7});
        test.add(new int[]{3});
        test.remove(6);
        assertEquals(test.getNode(7).toString(), "5 => 7 -> X X");
        test.remove(3);
        test.remove(7);
        assertEquals(test.getNode(5).toString(), "X => 5 -> X X");
        test.remove(5);
        assertEquals(test.printTree(), "");
        test.add(4);
        assertEquals(test.printTree(), "ROOT:4\n");
    }

    @Test
    public void getNode() {
        test = new Tree(5);
        test.add(new int[]{2, 6, 4, 1});
        assertEquals(test.printTree(), "ROOT:5\n" +
                "Left for 5 --> 2\n" +
                "Left for 2 --> 1\n" +
                "Right for 2 --> 4\n" +
                "Right for 5 --> 6\n");
        assertEquals(test.getNode(2).getValue(), 2);
        assertEquals(test.getNode(2, Tree.Searchmode.Parent).getValue(), 5);
        assertEquals(test.getNode(2, Tree.Searchmode.Right).getValue(), 4);
        assertEquals(test.getNode(2, Tree.Searchmode.Left).getValue(), 1);
        assertFalse(test.getNode(5, Tree.Searchmode.Parent).isInit());
        assertEquals(test.getNode(5, Tree.Searchmode.Parent).toString(), "X => 0 -> X X");
        assertEquals(test.getNode(5).toString(), "X => 5 -> 2 6");
        assertEquals(test.getNode(6).toString(), "5 => 6 -> X X");
        assertEquals(test.getNode(2).toString(), "5 => 2 -> 1 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicate() {
        test = new Tree(1);
        test.add(1);
    }

    @Test
    public void searchUndefined() {
        test = new Tree(1);
        assertFalse(test.getNode(2).isInit());
    }
}