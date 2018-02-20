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
        assertEquals("1 5 9", test.toString());
        assertEquals(Tree.printTree(test.getRoot()),
                "ROOT:5\n" +
                        "Left for 5 --> 1\n" +
                        "Right for 5 --> 9\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicate() {
        test = new Tree();
        test.add(1);
        test.add(1);
    }

    @Test
    public void removeFst() {
        test = new Tree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2}); // left subtree
        assertEquals(Tree.printTree(test.getRoot()),
                "ROOT:8\n" +
                        "Left for 8 --> 5\n" +
                        "Left for 5 --> 2\n" +
                        "Right for 8 --> 10\n");
        test.remove(5);
        assertEquals("2 8 10", test.toString());
        assertEquals(test.getNode(8, Tree.Searchmode.Left).value, 2);
        assertEquals(Tree.printTree(test.getRoot()),
                "ROOT:8\n" +
                        "Left for 8 --> 2\n" +
                        "Right for 8 --> 10\n");
    }

    @Test
    public void removeScd() {
        test = new Tree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2, 6, 7}); // left subtree
        test.remove(5);
        assertEquals("2 6 7 8 10", test.toString());
        assertEquals(test.getNode(8, Tree.Searchmode.Left).value, 6);
        assertEquals(Tree.printTree(test.getRoot()), "ROOT:8\n" +
                "Left for 8 --> 6\n" +
                "Left for 6 --> 2\n" +
                "Right for 6 --> 7\n" +
                "Right for 8 --> 10\n");
    }

    @Test
    public void removeThd() {
        test = new Tree(8); //root
        test.add(10); //right subtree
        test.add(new int[]{5, 2, 7, 6}); // left subtree
        assertEquals("2 5 6 7 8 10", test.toString());
        assertEquals(test.getNode(8, Tree.Searchmode.Left).value, 5);
        test.remove(5);
        assertEquals(test.getNode(8, Tree.Searchmode.Left).value, 6);
        assertFalse(test.getNode(6, Tree.Searchmode.Left).init);
        System.out.println(Tree.printTree(test.getRoot()));
    }
}