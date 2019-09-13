import java.util.List;

public class NodeStatementNested extends NodeStatement {

    List<NodeStatement> statements;

    public NodeStatementNested(List<NodeStatement> statements) {
        this.statements = statements;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeStatement statement : statements) {
            statement.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "Nested Statements: " + statements.toString();
    }

}
