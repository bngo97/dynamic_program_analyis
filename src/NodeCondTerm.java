import java.util.List;

public class NodeCondTerm extends Node {

    List<NodeCondFact> facts;

    public NodeCondTerm(List<NodeCondFact> facts) {
        this.facts = facts;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return facts.toString();
    }
}
