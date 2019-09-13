public class NodeStatementNoOp extends NodeStatement {

    public NodeStatementNoOp() {

    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "NO OP STATEMENT (break/continue/etc..)";
    }

}
