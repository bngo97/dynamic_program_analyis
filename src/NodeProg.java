import java.util.List;

public class NodeProg extends NodeDecl {

    List<NodeConstDecl> constDecls;
    List<NodeEnumDecl> enumDecls;
    List<NodeVarDecl> varDecls;
    List<NodeClassDecl> classDecls;

    public NodeProg(
            String id,
            List<NodeConstDecl> constDecls,
            List<NodeEnumDecl> enumDecls,
            List<NodeVarDecl> varDecls,
            List<NodeClassDecl> classDecls
        ) {
        super(id);
        this.constDecls = constDecls;
        this.enumDecls = enumDecls;
        this.varDecls = varDecls;
        this.classDecls = classDecls;
    }

    @Override
    public void accept(Visitor v) {

    }

}
