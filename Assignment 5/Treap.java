// Name: Gundeep Singh Saluja
// CWID: 20005427

import java.util.Stack;
import java.util.Random;

public class Treap<E extends Comparable<E>> {

    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority) throws IllegalArgumentException {
            if (data == null)
                throw new IllegalArgumentException("No data input!");
            else {
                this.data = data;
                this.priority = priority;
                this.left = null;
                this.right = null;
            }
        }

        public Node<E> rotateRight() {
            Node<E> new_root = new Node<E>(this.data, this.priority);
            if (this.left != null) {
                new_root.right = this.right;
                new_root.left = this.left.right;
                this.data = this.left.data;
                this.priority = this.left.priority;
                this.left = this.left.left;
                this.right = new_root;
            }
            return this;
        }

        public Node<E> rotateLeft() {
            Node<E> new_root = new Node<E>(this.data, this.priority);
            if (this.right != null) {
                new_root.left = this.left;
                new_root.right = this.right.left;
                this.data = this.right.data;
                this.priority = this.right.priority;
                this.right = this.right.right;
                this.left = new_root;
            }
            return this;
        }
    }

    private Node<E> root;
    private Random priorityGenerator;

    public Treap() {
        priorityGenerator = new Random();
        this.root = null;
    }

    public Treap(long seed) {
        priorityGenerator = new Random(seed);
        this.root = null;
    }

    boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    boolean add(E key, int priority) {
        Node<E> new_node = new Node<E>(key, priority);
        Node<E> temp_root = this.root;
        Stack<Node<E>> temp_stack = new Stack<Node<E>>();

        if (this.root == null) {
            this.root = new Node<E>(key, priority);
            return true;
        }
        if (find(key)) {
            return false;
        }
        while (temp_root != null) {
            temp_stack.push(temp_root);
            if ((key.compareTo(temp_root.data)) < 0)
                temp_root = temp_root.left;
            else
                temp_root = temp_root.right;
        }

        if ((key.compareTo((E) temp_stack.peek().data)) < 0)
            temp_stack.peek().left = new_node;
        else {
            temp_stack.peek().right = new_node;
        }
        temp_stack.push(new_node);
        reheap(temp_stack);
        return true;
    }

    private void reheap(Stack<Node<E>> new_stack) {
        Node<E> child = new_stack.pop();
        Node<E> parent = new_stack.pop();
        while (parent != null && child.priority > parent.priority) {
            if ((child.data.compareTo(parent.data)) > 0)
                parent.rotateLeft();
            else
                parent.rotateRight();
            if (!new_stack.isEmpty())
                parent = new_stack.pop();
            else
                parent = null;
        }
    }

    public boolean delete(E key) {
        if (find(key) == false || this.root == null) {
            return false;
        } else {
            this.root = delete(key, this.root);
            return true;
        }
    }

    private Node<E> delete(E key, Node<E> temp_root) {
        if (temp_root.data.compareTo(key) > 0)
            temp_root.left = delete(key, temp_root.left);
        else if (temp_root.data.compareTo(key) < 0)
            temp_root.right = delete(key, temp_root.right);
        if (temp_root.right == null)
            temp_root = temp_root.left;
        else if (temp_root.left == null)
            temp_root = temp_root.right;
        else {
            if (temp_root.right.priority > temp_root.left.priority) {
                temp_root = temp_root.rotateLeft();
                temp_root.left = delete(key, temp_root.left);
            } else {
                temp_root = temp_root.rotateRight();
                temp_root.right = delete(key, temp_root.right);
            }
        }
        return temp_root;
    }

    private boolean find(Node<E> root, E key) {
        if (root == null)
            return false;
        else if ((key.compareTo(root.data)) > 0)
            return find(root.right, key);
        else if ((key.compareTo(root.data)) < 0)
            return find(root.left, key);
        else
            return true;
    }

    public boolean find(E key) throws IllegalArgumentException {
        if (key == null)
            throw new IllegalArgumentException("Key can't be NULL!");
        else
            return find(this.root, key);
    }

    public String toString() {
        StringBuilder new_str = new StringBuilder();
        return preOrderTraversal(this.root, 1, new_str);
    }

    private String preOrderTraversal(Node<E> node, int depth, StringBuilder new_str) {

        for (int i = 1; i < depth; i++)
            new_str.append("  ");
        if (node == null)
            new_str.append("null\n");
        else {
            new_str.append("(key = " + node.data + ", priority = " + node.priority + ")\n\n");
            preOrderTraversal(node.left, depth + 1, new_str);
            preOrderTraversal(node.right, depth + 1, new_str);
        }
        return new_str.toString();
    }

    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();

        testTree.add(4, 19);
        testTree.add(2, 31);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);

        System.out.println(testTree.toString());

        testTree.delete(1);

        testTree.r

        System.out.println(testTree.toString());
    }
}