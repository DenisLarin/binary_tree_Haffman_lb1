/**
 * Created by denis__larin on 08.05.17.
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer,String> binaryTree = new BinaryTree<>();
       /* binaryTree.insert(10,"10");
        binaryTree.insert(5,"5");
        binaryTree.insert(124,"124");
        binaryTree.insert(3,"3");
        binaryTree.insert(4,"4");
        binaryTree.insert(1,"1");
        binaryTree.insert(2,"2");
        binaryTree.inOrder();*/
        Haffman haffman = new Haffman("it is my striiiiing!!!!");
        haffman.haffmanStart();
    }
}
