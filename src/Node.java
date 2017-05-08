import java.util.LinkedList;

/**
 * Created by denis__larin on 08.05.17.
 */
public class Node<K extends Comparable<K>,V> implements Comparable<K>{
    private V value;
    private K key;
    private Node<K,V> leftChild;
    private Node<K,V> rightChild;

    public Node(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public Node(Node<K, V> leftChild, Node<K, V> rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node<K, V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<K, V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<K, V> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key = " + key +
                ", value = " + value +
                '}';
    }

    @Override
    public int compareTo(K key) {
        return this.getKey().compareTo(key);
    }
}