
public class NodeStatementIf extends NodeStatement {

    NodeCondition condition;
    NodeStatement statement1;
    NodeStatement statement2;

    public NodeStatementIf(NodeCondition condition, NodeStatement statement1, NodeStatement statement2) {
        this.condition = condition;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        condition.accept(v);
        statement1.accept(v);
        if(statement2 != null) {
            statement2.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "statement if " + condition.toString() + "\nstatement1= " + statement1.toString()
                + "\nstatement2= " + (statement2 == null ? "null" : statement2.toString());
    }
}
