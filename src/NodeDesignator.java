import java.util.List;

public class NodeDesignator extends Node {

    List<String> ids;
    List<NodeExpr> expressions;

    public NodeDesignator(List<String> ids, List<NodeExpr> exprs) {
        this.ids = ids;
        this.expressions = exprs;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "ids: " + ids.toString() + "\nexprs: " + expressions.toString();
    }

}
