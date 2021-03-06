package mybinarytreeexample;

// Paul Chu
// C00124175
// CMPS 261
// Program Description: description of actions of code
// Certificate of Authenticity:
//            I certify that the code in the method functions
//            including method function main of this project are
//            entirely my own work.

public class MyBinaryTreeExample {
    public static void main(String[] args) {
        MyBinaryTree<Integer> mbt = new MyBinaryTree();

        mbt.insert(4);
        mbt.insert(20);
        mbt.insert(1);
        mbt.insert(9);
        mbt.insert(3);
        mbt.insert(3);
        mbt.insert(7);
        mbt.insert(2);
        mbt.insert(-1);
        mbt.insert(19);
        mbt.insert(-5);
        mbt.insert(-2);
        mbt.insert(22);
        mbt.insert(15);
        mbt.insert(11);
        mbt.insert(0);

        mbt.insert(17);
        mbt.insert(13);
        mbt.insert(-7);
        mbt.insert(-3);

        mbt.inorder();
        mbt.preorder();
        mbt.postorder();
        System.out.print("Breadth First Search: ");
        mbt.breadthFirstTraversal();

        System.out.println("\nIterator Example:");
        java.util.Iterator it = mbt.iterator();
        while (it.hasNext()) {
            System.out.printf("%3s", it.next());
        }
        System.out.println();

        System.out.println("\nDeleting Examples:\n");
        mbt.delete(4);
        mbt.preorder();
        mbt.delete(-5);
        mbt.preorder();
        mbt.delete(11);
        mbt.preorder();
        mbt.delete(3);
        mbt.preorder();
        mbt.delete(19);
        mbt.preorder();
        mbt.delete(20);
        mbt.preorder();
        mbt.delete(15);
        mbt.preorder();
        mbt.delete(1);
        mbt.preorder();
        mbt.delete(0);
        mbt.preorder();
        mbt.delete(-1);
        mbt.preorder();
        mbt.delete(1);
        mbt.preorder();
        mbt.delete(-2);
        mbt.preorder();
        mbt.delete(9);
        mbt.preorder();
        mbt.delete(7);
        mbt.preorder();
        mbt.delete(22);
        mbt.preorder();
        mbt.delete(2);
        mbt.preorder();


        System.out.println("Search for -7 : " + mbt.search(-7));
        mbt.delete(-3);
        mbt.preorder();
        mbt = mbt.deleteTree(mbt.getRoot());
        mbt.displayLeaves(mbt.getRoot());
        mbt.insert(9);
        mbt.preorder();
        mbt.insert(3);
        mbt.preorder();
        mbt.insert(3);
        mbt.preorder();
        mbt.insert(7);
        mbt.preorder();
    }
}
