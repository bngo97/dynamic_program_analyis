import java.util.List;

public class NodeTerm extends Node {

    List<NodeFactor> factors;

    public NodeTerm(List<NodeFactor> factors) {
        this.factors = factors;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeFactor factor : factors) {
            factor.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return factors.toString();
    }
}
