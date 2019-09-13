public class NodeStatementDesignator extends NodeStatement{

    NodeDesignator designator;

    public NodeStatementDesignator(NodeDesignator designator) {
        this.designator = designator;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "designator: " + designator.toString() + "\n";
    }

}
