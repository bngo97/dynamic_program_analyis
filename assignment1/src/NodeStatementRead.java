public class NodeStatementRead extends NodeStatement {

    NodeDesignator designator;

    public NodeStatementRead(NodeDesignator designator) {
        this.designator = designator;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        designator.accept(v);
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "Read statement designator: " + designator.toString();
    }
}
