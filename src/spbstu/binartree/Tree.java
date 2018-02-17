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
            return Search(cur.right, key);
    }

    public void Add(int key) {
        Node x = root, y = null;
        while (x != null) {
            if (key == x.value)
                throw new IllegalArgumentException("Duplicate keys");
            else {
                y = x;
                if (key < x.value)
                    x = x.left;
                else
                    x = x.right;
            }
        }
        Node res = new Node(key, y);
        if (y == null)
            root = res;
        else {
            if (y.value > key)
                y.left = res;
            else
                y.right = res;
        }
    }

    public void Add(int[] keys) {
        for (int k : keys) {
            this.Add(k);
        }
    }

    /**
     * @param key that must be removed
     */
    public void Remove(int key) {
        Node cur = Search(root, key), parent = cur.parent;
        if (cur == null) return;
        //First variant. Cur have only left subnode
        if (cur.right == null) {
            if (parent == null) {
                root = cur.left;
            } else {
                if (parent.left == cur) {
                    parent.left = cur.left;
                } else {
                    parent.right = cur.left;
                }
            }
        } else {
            Node left = cur.right;
            parent = null;
            while (left.left != null) {
                parent = left;
                left = left.left;
            }
            if (parent != null) {
                parent.left = left.right;
            } else {
                cur.right = left.right;
            }
            cur.value = left.value;
        }
    }

    private String print(Node t) {
        StringBuilder res = new StringBuilder();
        if (t != null) {
            res.append(print(t.left));
            res.append(t.value).append(" ");
            res.append(print(t.right));
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
        switch (mode) {
            case 0:
                return res;
            case 1:
                return res.parent;
            case 2:
                return res.left;
            default:
                return res.right;
        }
    }

    static class Node {
        int value;
        Node parent;
        Node left, right;

        Node(int value, Node p) {
            this.value = value;
            this.parent = p;
            right = null;
            left = null;
        }
    }
}
