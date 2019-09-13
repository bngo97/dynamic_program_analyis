public class NodeStatementReturn extends NodeStatement {

    NodeExpr expression;

    public NodeStatementReturn(NodeExpr expr) {
        this.expression = expr;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "statement return " + (expression == null ? "null" : expression.toString());
    }
}
