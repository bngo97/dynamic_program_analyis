import java.util.List;

public class NodeCondTerm extends Node {

    List<NodeCondFact> facts;

    public NodeCondTerm(List<NodeCondFact> facts) {
        this.facts = facts;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeCondFact fact : facts) {
            fact.accept(v);
        }
        v.visitEnd(this );
    }

    @Override
    public String toString() {
        return facts.toString();
    }
}
