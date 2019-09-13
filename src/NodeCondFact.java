public class NodeCondFact extends Node {

    NodeExpr expression1;
    // 2 may be null
    NodeExpr expression2;

    public NodeCondFact(NodeExpr expression1, NodeExpr expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "expression1: " + expression1.toString() + "\nexpression2: "
                + (expression2 == null ? "null" : expression2.toString());
    }
}
