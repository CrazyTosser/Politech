package spbstu.binartree;

public class Tree {

    private Node root;

    /**
     * @param val initial value of root node
     */
    public Tree(int val) {
        root = new Node(val, null);
    }

    /**
     * @param keys int array of values for adding to tree
     */
    public void add(int[] keys) {
        if (keys == null)
            return;
        for (int k : keys)
            this.add(k);
    }

    /**
     * Recursive method.
     *
     * @param head current node
     * @param val  adding value
     */
    private void add(Node head, int val) {
        if (head == null) {
            root = new Node(val, null);
            return;
        }
        Node tmp = head;
        while (tmp != null) {
            if (val > tmp.value) {
                if (tmp.right != null) {
                    tmp = tmp.right;
                } else {
                    tmp.right = new Node(val, tmp);
                    return;
                }
            } else if (val < tmp.value) {
                if (tmp.left != null) {
                    tmp = tmp.left;
                } else {
                    tmp.left = new Node(val, tmp);
                    return;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * @param key value for add to tree
     */
    public void add(int key) {
        this.add(root, key);
    }

    /**
     * @param target head node for new search
     * @return object Node with max value in current subtree
     */
    private Node findMaxNode(Node target) {
        while (target.right != null)
            target = target.right;
        return target;
    }

    /**
     * Recursive search
     * @param cur current node
     * @param key searching value
     * @return node with value = key
     */
    private Node search(Node cur, int key) {
        if (cur == null || key == cur.value)
            return cur;
        if (key < cur.value && cur.left != null)
            return search(cur.left, key);
        else if (cur.right != null)
            return search(cur.right, key);
        return null;
    }

    /**
     * @param key value that must be removed
     */
    public void remove(int key) {
        Node target = search(root, key);
        if (target == root)
            root = null;
        else
            this.remove(target);
    }

    /**
     * @param target node that must be removed
     */
    private void remove(Node target) {
        if (target.left != null && target.right != null) {
            Node localMax = findMaxNode(target.left);
            target.value = localMax.value;
            remove(localMax);
        } else if (target.left != null) {
            target.parent.left = target.left;
            target.parent.left.parent = target.parent;
        } else if (target.right != null) {
            target.parent.right = target.right;
            target.parent.right.parent = target.parent;
        } else {
            if (target.parent.isLeft(target)) {
                target.parent.left = null;
            } else {
                target.parent.right = null;
            }
        }
    }

    private String print(Node t) {
        StringBuilder res = new StringBuilder();
        if (t.left != null)
            res.append(print(t.left));
        res.append(t.value).append(" ");
        if (t.right != null)
            res.append(print(t.right));
        return res.toString();
    }

    public String printTree() {
        return printTree(root);
    }

    private String printTree(Node node) {
        StringBuilder str = new StringBuilder();
        if (node != null) {
            if (node.parent == null) {
                str.append("ROOT:").append(node.value).append("\n");
            } else {
                if (node.parent.isLeft(node)) {
                    str.append("Left for ").append(node.parent.value)
                            .append(" --> ").append(node.value).append("\n");
                }
                if (node.parent.isRight(node)) {
                    str.append("Right for ").append(node.parent.value)
                            .append(" --> ").append(node.value).append("\n");
                }
            }
            if (node.left != null)
                str.append(printTree(node.left));
            if (node.right != null)
                str.append(printTree(node.right));
        }
        return str.toString();
    }

    public String toString() {
        return print(root).trim();
    }

    public Node getNode(int key) {
        return getNode(key, Searchmode.Node);
    }

    /**
     * @param key  key of searching node
     * @param mode Node - get node, Parent - get parent
     *             Left - get left subnode, Right - get right subnode
     * @return searched node
     */
    public Node getNode(int key, Searchmode mode) {
        Node res = search(root, key);
        if (res == null)
            return new Node();
        switch (mode) {
            case Node:
                return res;
            case Parent:
                return res.parent != null ? res.parent : new Node();
            case Left:
                return res.left != null ? res.left : new Node();
            default:
                return res.right != null ? res.right : new Node();
        }
    }

    public static enum Searchmode {Node, Parent, Left, Right}

    public class Node {
        private boolean init = true;
        private int value;
        private Node parent;
        private Node left = null;
        private Node right = null;

        public Node(int val, Node parent) {
            value = val;
            this.parent = parent;
        }

        public boolean isInit() {
            return init;
        }

        public int getValue() {
            return value;
        }

        public Node() {
            init = false;
        }

        public boolean isLeft(Node p) {
            return left != null && p == left;
        }

        public boolean isRight(Node p) {
            return right != null && p == right;
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            if (parent == null)
                str.append("X");
            else
                str.append(parent.value);
            str.append(" => ").append(value).append(" -> ");
            if (left == null)
                str.append("X ");
            else
                str.append(left.value).append(" ");
            if (right == null)
                str.append("X");
            else
                str.append(right.value);
            return str.toString();
        }
    }
}

