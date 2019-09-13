import java.util.List;

public class NodeInterfaceDecl extends NodeDecl {

    List<NodeInterfaceMethodDecl> methods;

    public NodeInterfaceDecl(String id, List<NodeInterfaceMethodDecl> methods) {
        super(id);
        this.methods = methods;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "";
    }

}
