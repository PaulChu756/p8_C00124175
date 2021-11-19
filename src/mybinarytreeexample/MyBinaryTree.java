package mybinarytreeexample;

public class MyBinaryTree<E extends Comparable<E>> {

    private Node<E> root = null;
    private boolean isFlagged = false;

    /**
     * Problem 2 : boolean field
     *
     * Problem 3 : search method to see if element is searched
     *
     * Problem 4 : Delete all the nodes and use preorder traversal to create a new tree
     *
     * Problem 5: Display all nodes that was leaves, check if both child of nodes left and right are null
     * that means it must be leaf
     *
     * Problem 6: Print BST in breath first search from left to right
     * @param <E>
     */

    public class Node<E> {

        public E e = null;
        public Node<E> left = null;
        public Node<E> right = null;
        public boolean isFlagged = false;
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
                if(child.isFlagged == true){
                    child.isFlagged = false;
                    child.e = e;
                    return true;
                }
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

    public boolean delete(E e) {

        // binary search until found or not in list
        boolean found = false;
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
            if (child == root && root.left == null && root.right == null)
                root = null;
                // if leaf, remove
            else if (child.left == null && child.right == null) {
                if (parent.left == child)
                    parent.left = null;
                else
                    parent.right = null;
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

        return found;
    }

    /**
     * LazyDelete method just goes through the BST to locate a element, and mark it for deletion
     * @param e
     * @return true or false if element is found
     */
    public boolean lazyDelete(E e) {
        // binary search until found or not in list
        boolean found = false;
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
                child.isFlagged = true;
                break;
            }
        }
        return found;
    }

    /**
     * Search through the BST to see if it's found in BST
     * @param e
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

    public void deleteTree(Node<E> current){
        //using preorder travseral to delete
        if(current != null){
            current.isFlagged = true;
            preorder(current.left);
            preorder(current.right);
        }

        if(current != null){
            delete(current.e);
            preorder(current.left);
            preorder(current.right);
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
