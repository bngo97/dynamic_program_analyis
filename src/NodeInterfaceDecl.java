import java.util.List;

public class NodeInterfaceDecl extends NodeDecl {

    List<NodeInterfaceMethodDecl> methods;

    public NodeInterfaceDecl(String id, List<NodeInterfaceMethodDecl> methods) {
        super(id);
        this.methods = methods;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(NodeInterfaceMethodDecl method : methods) {
            method.accept(v);
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "Interface " + id;
    }

}
