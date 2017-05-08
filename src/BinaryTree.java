/**
 * Created by denis__larin on 08.05.17.
 */
public class BinaryTree<K extends Comparable<K>,V>{
    private Node<K,V> root;
    private Node<K,V> parent;
    private int size;

    public BinaryTree() {
        root = null;
        parent = null;
        size = 0;
    }
    public void insert(K key, V value){
        Node<K,V> insertNode = new Node<>(key, value);
        //дерево пустое
        if(root == null){
            root = insertNode;
        }
        //дерево не путое => ищем место, куда добавить новый узел
        else{
            addTo(root,insertNode);
        }
        size++;
    }

    //поиск по дереву вернет узел
    public Node<K,V> search(K key){
        Node<K,V> returnElement = root;
        while (returnElement!=null){
            int compareResult = returnElement.compareTo(key);
            //если ключ текущего элемента больше чем ключ искомого, то идем влево
            if(compareResult>0){
                parent = returnElement;
                returnElement = returnElement.getLeftChild();
            }
            else if(compareResult<0){
                parent = returnElement;
                returnElement = returnElement.getRightChild();
            }
            else
                break;
        }
        return returnElement;
    }

    public Node<K,V> remove(K key) {
        Node<K,V> removeNode = search(key);
        if (removeNode == null) {
            return null;
        }
        if(removeNode.getRightChild() == null) {
            //если удаляем корень то
            if (parent == null) {
                root = removeNode.getLeftChild();
            }
            //если не корень
            else {
                int compareResult = parent.compareTo(removeNode.getKey());
                //ключ родителя больше удаляемого ключа
                if (compareResult > 0) {

                } else if (compareResult < 0) {
                    parent.setRightChild(removeNode.getRightChild());
                }
            }
        }
        else if (removeNode.getRightChild().getLeftChild() ==null){
            removeNode.getRightChild().setLeftChild(removeNode.getLeftChild());
            if(parent == null){
                root = removeNode.getRightChild();
            }
            else{
                int compareResult = parent.compareTo(removeNode.getKey());
                if(compareResult>0){
                    parent.setLeftChild(removeNode.getRightChild());
                }
                else if (compareResult<0){
                    parent.setRightChild(removeNode.getRightChild());
                }
            }
        }
        else{
            Node<K,V> leftMost = removeNode.getRightChild().getLeftChild();
            Node<K,V> leftMostParent = removeNode.getRightChild();
            while (leftMost.getLeftChild()!=null){
                leftMostParent = leftMost;
                leftMost = leftMost.getLeftChild();
            }
            leftMostParent.setLeftChild(leftMost.getRightChild());
            leftMost.setLeftChild(removeNode.getLeftChild());
            leftMostParent.setRightChild(removeNode.getRightChild());
            if(parent == null){
                root = leftMost;
            }
            else{
                int compareResult = parent.compareTo(removeNode.getKey());
                if(compareResult>0){
                    parent.setLeftChild(leftMost);
                }
                else if (compareResult<0){
                    parent.setRightChild(leftMost );
                }
            }
        }
        size--;
        return removeNode;
    }
    //обход дерева inOrder -- левый=>корень=>правый
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node<K,V> curElement) {
        if(curElement.getLeftChild()!=null){
            inOrder(curElement.getLeftChild());
        }
        System.out.println(curElement.toString());
        if(curElement.getRightChild()!=null){
            inOrder(curElement.getRightChild());
        }
    }
    public int weightTree(){
        return weightTree(root);
    }

    private int weightTree(Node<K, V> curNode) {
        int maxWidth = 0;
        int level;
        int width = 0;
        int heigth = heightTree();
        //проходим по уровням и ищем ширину
        for (level = 0; level <heigth ; level++) {
            width = getWidth(root,level);
            if(width>maxWidth)
                maxWidth =width;
        }
        return maxWidth;
    }

    private int getWidth(Node<K, V> curNode, int level) {
        //если элемент пуст то его ширина 0
        if(curNode == null)
            return 0;
        //если это родитель, то у него только 1 элемент
        if(level == 1)
            return 1;
        //если уровнь больше первого, то есть если мы ниже root
        else if(level>1){
            return getWidth(curNode.getLeftChild(),level-1) + getWidth(curNode.getRightChild(),level-1);
        }
        getWidth(curNode.getRightChild(),level-1);
        return 0;
    }

    public int heightTree(){
        return heightTree(root);
    }
    //высота дерева
    private int heightTree(Node<K, V> curNode) {
        if(curNode == null){
            return 0;
        }
        return 1+Math.max(heightTree(curNode.getLeftChild()),heightTree(curNode.getRightChild()));
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node<K,V> curElement)  {
        if(curElement.getLeftChild()!=null){
            postOrder(curElement.getLeftChild());
        }
        if(curElement.getRightChild()!=null){
            postOrder(curElement.getRightChild());
        }
        System.out.println(curElement.toString());
    }
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node<K,V> curElement) {
        System.out.println(curElement.toString());
        if(curElement.getLeftChild()!=null){
            preOrder(curElement.getLeftChild());
        }
        if(curElement.getRightChild()!=null){
            preOrder(curElement.getRightChild());
        }
    }

    public int getSize() {
        return size;
    }

    private void addTo(Node<K,V> curNode, Node<K,V> insertNode) {
        //если ключ добавляемгого элемента меньше чем ключ текущего элемента
        if (insertNode.getKey().compareTo(curNode.getKey()) < 0) {
            //если нет левого ребенка, то добаляем его
            if (curNode.getLeftChild() == null) {
                curNode.setLeftChild(insertNode);
                return;
            } else
                addTo(curNode.getLeftChild(), insertNode);
        }
        //заначение добавляемого ключа больше
        else{
            if (curNode.getRightChild() == null) {
                curNode.setRightChild(insertNode);
                return;
            } else
                addTo(curNode.getRightChild(), insertNode);
        }
    }

    public Node<K, V> getRoot() {
        return root;
    }
}
