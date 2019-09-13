import java.util.List;

public class NodeProg extends NodeDecl {

    List<NodeConstDecl> constDecls;
    List<NodeEnumDecl> enumDecls;
    List<NodeVarDecl> varDecls;
    List<NodeClassDecl> classDecls;
    List<NodeMethodDecl> methods;

    public NodeProg(
            String id,
            List<NodeConstDecl> constDecls,
            List<NodeEnumDecl> enumDecls,
            List<NodeVarDecl> varDecls,
            List<NodeClassDecl> classDecls,
            List<NodeMethodDecl> methods
        ) {
        super(id);
        this.constDecls = constDecls;
        this.enumDecls = enumDecls;
        this.varDecls = varDecls;
        this.classDecls = classDecls;
        this.methods = methods;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeConstDecl constDecl : constDecls) {
            constDecl.accept(v);
        }
        for(NodeEnumDecl enumDecl : enumDecls) {
            enumDecl.accept(v);
        }
        for(NodeVarDecl var : varDecls) {
            var.accept(v);
        }
        for(NodeClassDecl classDecl : classDecls) {
            classDecl.accept(v);
        }
        for(NodeMethodDecl method : methods) {
            method.accept(v);
        }
        v.visitEnd(this);
    }

}
