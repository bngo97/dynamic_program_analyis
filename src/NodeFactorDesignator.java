public class NodeFactorDesignator extends NodeFactor {

    NodeDesignator designator;
    NodeActPars actPars;

    public NodeFactorDesignator(NodeDesignator designator, NodeActPars actPars) {
        this.designator = designator;
        this.actPars = actPars;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "factor designator: " + designator.toString() + "\nactPars: " + (actPars == null ? "null" : actPars.toString());
    }

}
