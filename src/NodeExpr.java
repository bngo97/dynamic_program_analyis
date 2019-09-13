import java.util.List;

public class NodeExpr extends Node {

    List<NodeTerm> terms;

    public NodeExpr(List<NodeTerm> terms) {
        this.terms = terms;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "terms: " + terms.toString();
    }
}
