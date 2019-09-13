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
        v.visit(this);
        designator.accept(v);
        if(expression != null) {
            expression.accept(v);
        }
        if(actPars != null) {
            actPars.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "designator: " + designator.toString() + "\n"
                + "expression: " + (expression == null ? "null" : expression.toString()) + "\n"
                + "actPars: " + (actPars == null ? "null" : actPars.toString());
    }

}
