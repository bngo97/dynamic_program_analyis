import java.util.List;
import java.util.Map;

public class NodeMethodDecl extends NodeDecl {

    String returnType;
    // map from name to type for input parameters
    Map<String,String> formPars;
    List<NodeVarDecl> vars;
    List<NodeStatement> statements;

    public NodeMethodDecl(String id, String returnType, Map<String,String> formPars, List<NodeVarDecl> vars, List<NodeStatement> statements) {
        super(id);
        this.returnType = returnType;
        this.formPars = formPars;
        this.vars = vars;
        this.statements = statements;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeVarDecl var : vars) {
            var.accept(v);
        }
        for(NodeStatement statement : statements) {
            statement.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "method " + id + " return " + returnType + " input=" + formPars.toString() + "\nvars=" + vars.toString() + "\n" + statements.toString();
    }
}
