package spbstu.binartree;

import java.util.Optional;

public class Node {
    public int value;
    private Optional<Node> parent = Optional.empty();
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
        StringBuilder str = new StringBuilder();
        str.append(this.getParent().map(n -> "" + n.value).orElse("X")).append(" => ");
        str.append(this.value).append(" -> ");
        str.append(this.getLeft().map(n -> "" + n.value).orElse("X")).append(" , ");
        str.append(this.getRight().map(n -> "" + n.value).orElse("X"));
        return str.toString();
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
