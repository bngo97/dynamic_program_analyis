import java.util.List;

public class NodeExpr extends Node {

    List<NodeTerm> terms;

    public NodeExpr(List<NodeTerm> terms) {
        this.terms = terms;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeTerm term : terms) {
            term.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "terms: " + terms.toString();
    }

}
