public class NodeStatementDesignator extends NodeStatement{

    NodeDesignator designator;
    NodeExpr expression;
    NodeActPars actPars;

    public NodeStatementDesignator(NodeDesignator designator, NodeExpr expr, NodeActPars actPars) {
        this.designator = designator;
        this.expression = expr;
        this.actPars = actPars;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "designator: " + designator.toString() + "\n"
                + "expression: " + (expression == null ? "null" : expression.toString()) + "\n"
                + "actPars: " + (actPars == null ? "null" : actPars.toString());
    }

}
