package mybinarytreeexample;

public class MyBinaryTree<E extends Comparable<E>> {

    private Node<E> root = null;

    public class Node<E> {
        public E e = null;
        public Node<E> left = null;
        public Node<E> right = null;
        //public boolean isFlagged = false;
    }

    public boolean insert(E e) {
        // if empty tree, insert a new node as the root node
        // and assign the elementy to it
        if (root == null) {
            root = new Node();
            root.e = e;
            return true;
        }

        // otherwise, binary search until a null child pointer 
        // is found
        Node<E> parent = null;
        Node<E> child = root;

        while (child != null) {
            if (e.compareTo(child.e) < 0) {
                parent = child;
                child = child.left;
            } else if (e.compareTo(child.e) > 0) {
                parent = child;
                child = child.right;
            } else {
                return false;
            }
        }

        // if e < parent.e create a new node, link it to 
        // the binary tree and assign the element to it
        if (e.compareTo(parent.e) < 0) {
            parent.left = new Node();
            parent.left.e = e;
        } else {
            parent.right = new Node();
            parent.right.e = e;
        }
        return true;
    }

    public void inorder() {
        System.out.print("inorder:   ");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> current) {
        if (current != null) {
            inorder(current.left);
            System.out.printf("%3s", current.e);
            inorder(current.right);
        }
    }

    public void preorder() {
        System.out.print("preorder:  ");
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<E> current) {
        if (current != null) {
            System.out.printf("%3s", current.e);
            preorder(current.left);
            preorder(current.right);
        }
    }

    public void postorder() {
        System.out.print("postorder: ");
        postorder(root);
        System.out.println();
    }

    private void postorder(Node<E> current) {
        if (current != null) {
            postorder(current.left);
            postorder(current.right);
            System.out.printf("%3s", current.e);
        }
    }


    //Problem 2
    /**
     * Delete the node, but modfited it to flag nodes that have been deleted
     * @param e node to delete in BST
     * @return returns true or false if node was deleted
     */
    public boolean delete(E e) {

        // binary search until found or not in list
        boolean found = false;
        boolean isFlagged = false;
        Node<E> parent = null;
        Node<E> child = root;

        while (child != null) {
            if (e.compareTo(child.e) < 0) {
                parent = child;
                child = child.left;
            } else if (e.compareTo(child.e) > 0) {
                parent = child;
                child = child.right;
            } else {
                found = true;
                break;
            }
        }


        if (found) {
            // if root only is the only node, set root to null
            if (child == root && root.left == null && root.right == null) {
                root = null;
                isFlagged = true;
            }
                // if leaf, remove
            else if (child.left == null && child.right == null) {
                if (parent.left == child) {
                    parent.left = null;
                    isFlagged = true;
                }
                else {
                    parent.right = null;
                    isFlagged = true;
                }
            } else
                // if the found node is not a leaf
                // and the found node only has a right child,
                // connect the parent of the found node (the one
                // to be deleted) to the right child of the
                // found node
                if (child.left == null) {
                    if (parent.left == child)
                        parent.left = child.right;
                    else
                        parent.right = child.right;
                } else {
                    // if the found node has a left child,
                    // the node in the left subtree with the largest element
                    // (i. e. the right most node in the left subtree)
                    // takes the place of the node to be deleted
                    Node<E> parentLargest = child;
                    Node<E> largest = child.left;
                    while (largest.right != null) {
                        parentLargest = largest;
                        largest = largest.right;
                    }

                    // replace the element in the found node with the element in
                    // the right most node of the left subtree
                    child.e = largest.e;

                    // if the parent of the node of the largest element in the
                    // left subtree is the found node, set the left pointer of the
                    // found node to point to left child of its left child
                    if (parentLargest == child)
                        child.left = largest.left;
                    else
                        // otherwise, set the right child pointer of the parent of
                        // largest element in the left subtree to point to the left
                        // subtree of the node of the largest element in the left
                        // subtree
                        parentLargest.right = largest.left;
                }

        } // end if found

        return isFlagged;
    }

    //Problem 3
    /**
     * Search through the BST to see if it's found in BST
     * @param e node to search for in BST
     * @return true or false if element is found
     */
    public boolean search(E e){
        boolean found = false;
        Node<E> current = root;

        while (current != null) {
            if (e.compareTo(current.e) < 0) {
                current = current.left;
            } else if (e.compareTo(current.e) > 0) {
                current = current.right;
            } else {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * getRoot returns the root of the tree
     * Will need this when deleting the tree
     * @return the root of the tree
     */
    public Node<E> getRoot(){
        return root;
    }

    //Problem 4
    /**
     * deleteTree
     * using preorder to delete the nodes that are marked to be deleted
     * @param current delete the nodes and insert them into a different tree
     */
    public MyBinaryTree deleteTree(Node<E> current){
        //using preorder travseral to delete
        MyBinaryTree tempTree = new MyBinaryTree();

        if(current != null){
            tempTree.insert(current.e);
            preorder(current.left);
            preorder(current.right);
        }
        return tempTree;
    }

    //Problem 5
    /**
     * Display leaves just displays all the leaf nodes
     * @param current pass node and see which leaves are returns
     */
    public void displayLeaves(Node<E> current){
        if(current != null){
            if(current.left == null && current.right == null){
                System.out.println(current.e);
            }
            preorder(current.left);
            preorder(current.right);
        }
    }


    //Problem 6
    /**
     * breadthFirstTraversal function to search and print the tree in
     * Breadth first search traversal
     */
    public void breadthFirstTraversal(){
        int h = getHeight(root);
        int i;
        for(i = 1; i <= h; i++)
            printCurrentLevel(root, i);
    }

    /**
     * getheight gets the height of the tree
     * @param root pass root node to get height of tree
     * @return the height of the tree in int
     */
    //get the height of the tree
    public int getHeight(Node root){
        if(root == null){
            return 0;
        }
        else{
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            if(leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }
    }

    /**
     * prints the current level of the tree
     * @param root pass current node
     * @param level pass which level your on
     */
    //print from bottom to top
    public void printCurrentLevel(Node root, int level){
        if(root == null)
            return;
        if(level == 1)
            System.out.print(root.e + " ");
        else if(level > 1){
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }


    // an iterator allows elements to be modified, but can mess with
    // the order if element not written with immutable key; it is better
    // to use delete to remove and delete/insert to remove or replace a
    // node
    public java.util.Iterator<E> iterator() {
        return new PreorderIterator();
    }

    private class PreorderIterator implements java.util.Iterator<E> {

        private java.util.LinkedList<E> ll = new java.util.LinkedList();
        private java.util.Iterator<E> pit = null;

        // create a LinkedList object that uses a linked list of nodes that
        // contain references to the elements of the nodes of the binary tree 
        // in preorder
        public PreorderIterator() {
            buildListInPreorder(root);
            pit = ll.iterator();
        }

        private void buildListInPreorder(Node<E> current) {
            if (current != null) {
                ll.add(current.e);
                buildListInPreorder(current.left);
                buildListInPreorder(current.right);
            }
        }

        // check to see if their is another node in the LinkedList
        @Override
        public boolean hasNext() {
            return pit.hasNext();
        }

        // reference the next node in the LinkedList and return a 
        // reference to the element in the node of the binary tree
        @Override
        public E next() {
            return pit.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("NO!");
        }
    }
}
