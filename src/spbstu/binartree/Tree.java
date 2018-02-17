package spbstu.binartree;

import java.util.NoSuchElementException;

public class Tree {
    private Node root;

    /**
     * @param KeyRoot initial value for root node of new tree
     */
    public Tree(int KeyRoot) {
        this.root = new Node(KeyRoot, null);
    }

    Tree() {
        this.root = null;
    }

    /**
     * @param cur current node for recursive search
     * @param key search key
     * @return node with the search key
     */
    private Node Search(Node cur, int key) {
        if (cur == null || key == cur.value)
            return cur;
        if (key < cur.value)
            return Search(cur.left, key);
        else
            return Search(cur.rigth, key);
    }

    public void Add(int key) {
        Node x = root, y = null;
        while (x != null) {
            if (key > x.value) {
                if (x.rigth != null) {
                    y = x;
                    x = x.rigth;
                } else {
                    x.rigth = new Node(key, y);
                    break;
                }
            } else {
                if (key < x.value) {
                    if (x.left != null) {
                        y = x;
                        x = x.left;
                    } else {
                        x.left = new Node(key, y);
                        break;
                    }
                } else throw new IllegalArgumentException("Duplicate key");
            }
        }

    }

    public void Add(int[] keys) {
        for (int k : keys) {
            this.Add(k);
        }
    }

    /**
     * @param x current node
     * @return node with minimum key
     */
    private Node Minimum(Node x) {
        if (x.left == null)
            return x;
        return Minimum(x.left);
    }

    /**
     * @param key that must be removed
     */
    public void Remove(int key) {
        Node cur = Search(root, key), parent = cur.parent;
        if (cur == null) return;
        //First variant. Cur have only left subnode
        if (cur.rigth == null) {
            if (parent.value > cur.value) {
                parent.left = cur.left;
            } else {

            }
        }
    }

    private String print(Node t) {
        StringBuilder res = new StringBuilder();
        if (t != null) {
            res.append(print(t.left));
            res.append(t.value).append(" ");
            res.append(print(t.rigth));
        }
        return res.toString();
    }

    public String toString() {
        return print(root).trim();
    }

    public Node getNode(int key) {
        return getNode(key, 0);
    }

    /**
     * @param key  key of searching node
     * @param mode 0 - get node, 1 - get parent
     *             2 - get left subnode, 3 - get right subnode
     * @return searched node
     */
    public Node getNode(int key, int mode) {
        Node res = Search(root, key);
        if (res == null)
            throw new NoSuchElementException("This key not found or have not parent ");
        if (mode == 0) {
            return res;
        } else if (mode == 1) {
            return res.parent;
        } else if (mode == 2) {
            return res.left;
        } else {
            return res.rigth;
        }
    }

    static class Node {
        int value;
        Node parent;
        Node left, rigth;

        Node(int value, Node p) {
            this.value = value;
            this.parent = p;
            rigth = null;
            left = null;
        }
    }
}
