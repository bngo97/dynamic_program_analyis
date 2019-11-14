import java.util.List;

public class NodeCondition extends Node {

    List<NodeCondTerm> terms;

    public NodeCondition(List<NodeCondTerm> terms) {
        this.terms = terms;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeCondTerm term : terms) {
            term.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return terms.toString();
    }

}
