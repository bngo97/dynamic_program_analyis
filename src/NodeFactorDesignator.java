public class NodeFactorDesignator extends NodeFactor {

    NodeDesignator designator;
    NodeActPars actPars;

    public NodeFactorDesignator(NodeDesignator designator, NodeActPars actPars) {
        this.designator = designator;
        this.actPars = actPars;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        designator.accept(v);
        if(actPars != null) {
            actPars.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "factor designator: " + designator.toString() + "\nactPars: " + (actPars == null ? "null" : actPars.toString());
    }

}
