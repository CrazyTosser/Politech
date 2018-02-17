package spbstu.binartree;

public class Tree {
    private Node root;

    /**
     * @param KeyRoot initial value for root node of new tree
     */
    Tree(int KeyRoot) {
        this.root = new Node(KeyRoot, null);
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

    public void Add(int KeyParent, int KeyChild) {
        Node x = root, y = null;
        while (x != null) {
            if (KeyChild > KeyParent) {
                if (x.rigth != null) {
                    x = x.rigth;
                } else {
                    x.rigth = new Node(KeyChild, x);
                    break;
                }
            } else {
                if (KeyChild < KeyParent) {
                    if (x.left != null) {
                        x = x.left;
                    } else {
                        x.left = new Node(KeyChild, x);
                        break;
                    }
                } else throw new IllegalArgumentException("Duplicate key");
            }
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
     * @param root current root of subtree
     * @param key  wich key must be deleted
     * @return current root of subtree (recursive)
     */
    private Node Remove(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.value) {
            root.left = Remove(root.left, key);
        } else if (key > root.value) {
            root.rigth = Remove(root.rigth, key);
        } else if (root.left != null && root.rigth != null) {
            root.value = Minimum(root).value;
            root.rigth = Remove(root.rigth, root.value);
        } else {
            if (root.left != null)
                root = root.left;
            else
                root = root.rigth;
        }
        return root;
    }

    /**
     * @param key that must be removed
     */
    public void Remove(int key) {
        Remove(root, key);
    }

    static class Node {
        int value;
        Node parent;
        Node left = null, rigth = null;

        Node(int value, Node p) {
            this.value = value;
            this.parent = p;
        }
    }
}
