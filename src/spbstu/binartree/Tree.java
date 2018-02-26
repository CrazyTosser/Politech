package spbstu.binartree;

import java.util.Optional;

public class Tree {

    private Node root;

    public Tree(int val) {
        root = new Node(val, null);
    }

    public void add(int[] keys) {
        for (int k : keys) {
            this.add(k);
        }
    }

    public void add(int key) {
        this.add(root, key);
    }

    private void add(Node head, int val) {
        Node tmp = head, ins = null;
        while (tmp != null) {
            if (val > tmp.value) {
                if (tmp.getRight().isPresent()) {
                    tmp = tmp.getRight().get();
                } else {
                    tmp.setRight(new Node(val, tmp));
                    return;
                }
            } else if (val < tmp.value) {
                if (tmp.getLeft().isPresent()) {
                    tmp = tmp.getLeft().get();
                } else {
                    tmp.setLeft(new Node(val, tmp));
                    return;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private Node findMaxNode(Node target) {
        while (target.getRight().isPresent())
            target = target.getRight().get();
        return target;
    }

    private Node search(Node cur, int key) {
        if (cur == null || key == cur.value)
            return cur;
        if (key < cur.value && cur.getLeft().isPresent())
            return search(cur.getLeft().get(), key);
        else if (cur.getRight().isPresent())
            return search(cur.getRight().get(), key);
        return null;
    }

    public void remove(int key) {
        Node target = search(root, key);
        this.remove(target);
    }

    private void remove(Node target) {
        if (target.getLeft().isPresent() && target.getRight().isPresent()) {
            Node localMax = findMaxNode(target.getLeft().get());
            target.value = localMax.value;
            remove(localMax);
        } else if (target.getLeft().isPresent()) {
            if (target.getParent().isPresent()
                    && target == target.getParent().get().getLeft().get()) {
                target.getParent().get().setLeft(target.getLeft().get());
                target.getParent().get().getLeft().get().setParent(target.getParent().get());
            } else {
                target.getParent().get().setRight(target.getLeft().get());
                target.getParent().get().getRight().get().setParent(target.getParent().get());
            }
        } else if (target.getRight().isPresent()) {
            if (target.getParent().isPresent()
                    && target == target.getParent().get().getRight().get()) {
                target.getParent().get().setRight(target.getRight().get());
                target.getParent().get().getRight().get().setParent(target.getParent().get());
            } else {
                target.getParent().get().setLeft(target.getRight().get());
                target.getParent().get().getLeft().get().setParent(target.getParent().get());
            }
        } else {
            if (target.getParent().isPresent()
                    && target == target.getParent().get().getLeft().get()) {
                target.getParent().get().setLeft(null);
            } else {
                target.getParent().get().setRight(null);
            }
        }
    }

    public String printTree() {
        return printTree(root);
    }


    private String print(Node t) {
        StringBuilder res = new StringBuilder();
        if (t.getLeft().isPresent()) res.append(print(t.getLeft().get()));
        res.append(t.value).append(" ");
        if (t.getRight().isPresent()) res.append(print(t.getRight().get()));
        return res.toString();
    }

    public String toString() {
        return print(root).trim();
    }

    private String printTree(Node node) {
        StringBuilder str = new StringBuilder();
        if (node != null) {
            if (!node.getParent().isPresent()) {
                str.append("ROOT:").append(node.value).append("\n");
            } else {
                if (node.getParent().get().isLeft(node)) {
                    str.append("Left for ").append(node.getParent().get().value)
                            .append(" --> ").append(node.value).append("\n");
                }
                if (node.getParent().get().isRight(node)) {
                    str.append("Right for ").append(node.getParent().get().value)
                            .append(" --> ").append(node.value).append("\n");
                }
            }
            if (node.getLeft().isPresent())
                str.append(printTree(node.getLeft().get()));
            if (node.getRight().isPresent())
                str.append(printTree(node.getRight().get()));
        }
        return str.toString();
    }

    class Node {
        public int value;
        private Optional<Node> parent;
        private Optional<Node> left = Optional.empty();
        private Optional<Node> right = Optional.empty();

        public Node(int val, Node parent) {
            value = val;
            this.parent = Optional.ofNullable(parent);
        }

        public boolean isLeft(Node p) {
            return getLeft().isPresent() && p == getLeft().get();
        }

        public boolean isRight(Node p) {
            return getRight().isPresent() && p == getRight().get();
        }

        public String toString() {
            return this.getParent().map(n -> "" + n.value).orElse("X") + " => " +
                    this.value + " -> " +
                    this.getLeft().map(n -> "" + n.value).orElse("X") + " , " +
                    this.getRight().map(n -> "" + n.value).orElse("X");
        }

        public Optional<Node> getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = Optional.ofNullable(parent);
        }

        public Optional<Node> getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = Optional.ofNullable(left);
        }

        public Optional<Node> getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = Optional.ofNullable(right);
        }

    }
}

