import java.util.Comparator;

/**
 * Created by denis__larin on 08.05.17.
 */
public class Comp implements Comparator<Node<Integer,Character>> {

    @Override
    public int compare(Node<Integer, Character> o1, Node<Integer, Character> o2) {
        return o1.getKey().compareTo(o2.getKey());
    }
}
