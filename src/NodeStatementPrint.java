public class NodeStatementPrint extends NodeStatement {

    NodeExpr expression;
    String val;

    public NodeStatementPrint(NodeExpr expression, String val) {
        this.expression = expression;
        this.val = val;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "Print statement, expression: " + expression.toString();
    }

}
