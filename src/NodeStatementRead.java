public class NodeStatementRead extends NodeStatement {

    NodeDesignator designator;

    public NodeStatementRead(NodeDesignator designator) {
        this.designator = designator;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "Read statement designator: " + designator.toString();
    }
}
