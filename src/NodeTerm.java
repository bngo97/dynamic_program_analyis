import java.util.List;

public class NodeTerm extends Node {

    List<NodeFactor> factors;

    public NodeTerm(List<NodeFactor> factors) {
        this.factors = factors;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return factors.toString();
    }
}
