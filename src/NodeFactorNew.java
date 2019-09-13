public class NodeFactorNew extends NodeFactor {

    String type;
    NodeExpr expression;

    public NodeFactorNew(String type, NodeExpr expr) {
        this.type = type;
        this.expression = expr;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "new " + type + " w/ expression: " + (expression == null ? "null" : expression.toString());
    }

}
