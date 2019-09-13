import java.util.List;

public class NodeStatementNested extends NodeStatement {

    List<NodeStatement> statements;

    public NodeStatementNested(List<NodeStatement> statements) {
        this.statements = statements;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "Nested Statements: " + statements.toString();
    }

}
