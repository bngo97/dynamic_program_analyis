import java.util.List;

public class NodeCondition extends Node {

    List<NodeCondTerm> terms;

    public NodeCondition(List<NodeCondTerm> terms) {
        this.terms = terms;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return terms.toString();
    }

}
