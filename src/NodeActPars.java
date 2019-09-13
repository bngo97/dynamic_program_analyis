import java.util.List;

public class NodeActPars extends Node {

    List<NodeExpr> expressions;

    public NodeActPars(List<NodeExpr> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return expressions.toString();
    }
}
