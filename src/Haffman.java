import java.util.*;

/**
 * Created by denis__larin on 08.05.17.
 */
public class Haffman {
    private final String str;
    private Map<Character, Integer> map = new HashMap<>();
    private LinkedList<Node<Integer, Character>> linkedList = new LinkedList<>();
    private Vector<Integer> code = new Vector<>();
    private Map<Character,String> tableMap = new HashMap<>();
    private Node root;

    public Haffman(String str) {
        this.str = str;
    }

    public void haffmanStart() {
        makeMap();
        makeList();
        makeTree();
        //preOrder();
        buildTable(root);
        printTable();
    }

    private void printTable() {
        for (Map.Entry entry :
                tableMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private void buildTable(Node<Integer,Character> curElement) {
        if(curElement.getLeftChild()!=null){
            code.addElement(0);
            buildTable(curElement.getLeftChild());
        }
        if(curElement.getRightChild()!=null){
            code.addElement(1);
            buildTable(curElement.getRightChild());
        }
        else{
            String key = "";
            for (int i = 0; i < code.size(); i++) {
                key+=code.get(i);
            }
            tableMap.put(curElement.getValue(),key);
        }
        if(code.size()>0){
            code.remove(code.size()-1);
        }
    }

    private void makeTree() {
        while (linkedList.size() != 1) {
            linkedList.sort(new Comp());
            System.out.println(linkedList);
            Node<Integer, Character> leftChild = linkedList.removeFirst();
            Node<Integer, Character> rigthChild = linkedList.removeFirst();
            Node<Integer, Character> parent = new Node<Integer, Character>(leftChild, rigthChild);
            parent.setKey(leftChild.getKey() + rigthChild.getKey());
            linkedList.addFirst(parent);
        }
        root = linkedList.removeLast();
    }

    private void makeList() {
        for (Map.Entry entry :
                map.entrySet()) {
            Node<Integer, Character> node = new Node((Comparable) entry.getValue(), entry.getKey());
            linkedList.add(node);
        }
    }

    //переносим строку в ассоциативный массив
    private void makeMap() {
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<Integer, Character> curElement) {
        System.out.println(curElement.toString());
        if (curElement.getLeftChild() != null) {
            preOrder(curElement.getLeftChild());
        }
        if (curElement.getRightChild() != null) {
            preOrder(curElement.getRightChild());
        }
    }
}
