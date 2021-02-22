package Tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;
        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int item) {
        Node node = new Node(item);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (true) {
            if (current.value > node.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }
            else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        if (root == null)
            return false;
        Node current = root;
        while (current != null) {
            if (current.value == value)
                return true;
            if (current.value > value)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return false;
    }

    // Tree traversal
    public void preorder() {
        prorder(root);
    }
    private void prorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        prorder(node.leftChild);
        prorder(node.rightChild);
    }

    // Ascending order
    public void inorder() {
        inorder(root);
    }
    private void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.leftChild);
        System.out.print(node.value + " ");
        inorder(node.rightChild);
    }

    public void inorderdescending() {
        inorderdescending(root);
    }
    private void inorderdescending(Node node) {
        if (node == null)
            return;
        inorderdescending(node.rightChild);
        System.out.print(node.value + " ");
        inorderdescending(node.leftChild);
    }

    public void postorder() {
        postorder(root);
    }
    private void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.leftChild);
        postorder(node.rightChild);
        System.out.print(node.value + " ");
    }

    //---------------------------------------------------------------------------
    // we used postorder traversal (we visit the leaves first)
    public int hight() {
        return hight(root);
    }
    private int hight(Node node) {
        if (node == null)
            return -1;
        int left = hight(node.leftChild);
        int right = hight(node.rightChild);
        return Math.max(left, right) + 1;
    }

    //------------------------------------------------------------
    // In case of binary tree
    public int min() {
        if (root == null)
            throw new IllegalStateException();
        return min(root);
    }
    // O(n)
    private int min(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.leftChild == null && node.rightChild == null)
            return node.value;
        return Math.min( Math.min(min(node.rightChild), min(node.leftChild)), node.value );
    }

    // Binary search tree
    // O(log(n))
    public int minBST() {
        if (root == null)
            throw new IllegalStateException();
        Node current = root;
        while (current.leftChild != null)
            current = current.leftChild;
        return current.value;
    }

    //------------------------------------------------------------------------
    // Check if two trees are equal
    public boolean  equals(Tree tree) {
        if (tree == null)
            return false;
        return equals(tree.root, this.root);
    }
    private boolean  equals(Node node1, Node node2) {

        // The two nodes are null
        if (node1 ==  null && node2 == null)
            return true;

        // The two nodes aren't null
        if (node1 != null && node2 != null)
            return node1.value == node2.value
                    && equals(node1.leftChild, node2.leftChild)
                    && equals(node1.rightChild, node2.rightChild);

        // one of them null and the other isn't
        return false;
    }

    //-------------------------------------------------------------------------
    // Validating a Binary search tree
    public boolean validBST() {
        return validBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean validBST(Node node, int minValue, int maxValue) {
        if (node == null)
            return true;

        return node.value > minValue && node.value < maxValue
                && validBST(node.leftChild, minValue, node.value)
                && validBST(node.rightChild, node.value, maxValue);
    }


    // Another Solution using in order traversal to check if the final output is sorting or not
    // if it is then it is BST
    public boolean isValidBST() {
        List<Integer> list = new ArrayList<Integer>();
        inOrderTraversal(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1))
                return false;
        }
        return true;
    }

    private void inOrderTraversal(Node node, List nums) {
        if (node == null)
            return;
        inOrderTraversal(node.leftChild, nums);
        nums.add(node.value);
        inOrderTraversal(node.rightChild, nums);

    }
    //-------------------------------------------------------------------------------

    // print the values in k-distance from the root
    public void printAtKDistance(int distance) {
        printAtKDistance(root, distance);
    }

    private void printAtKDistance(Node node, int distance) {
        if (node == null)
            return;

        if (distance == 0) {
            System.out.print(node.value + " ");
            return;
        }

        printAtKDistance(node.rightChild, distance - 1);
        printAtKDistance(node.leftChild, distance - 1);

    }

    //------------------------------------------------------------------------------
    

    
    public void deleteLeafes() {
        if (root == null)
            throw new IllegalStateException();
        deleteLeafes(root);
    }

    private void deleteLeafes(Node node) {
        if (node == null)
            return;
        if (node.leftChild != null && isLeaf(node.leftChild))
            node.leftChild = null;

        if (node.rightChild != null && isLeaf(node.rightChild))
            node.rightChild = null;

        deleteLeafes(node.rightChild);
        deleteLeafes(node.leftChild);
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

}
